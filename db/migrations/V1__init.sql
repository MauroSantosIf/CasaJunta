-- SCHEMA

create extension if not exists pgcrypto;

-- SYNC

create sequence sync_version_seq;

create or replace function set_sync_fields() returns trigger
language plpgsql security definer set search_path = public as $$
begin
    new.updated_at := now();
    new.server_version := nextval('sync_version_seq');
    return new;
end $$;

-- CONTAS

create table users (
    id uuid primary key default gen_random_uuid(),
    email text not null,
    password_hash text not null,               -- bcrypt via pgcrypto
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
);
create unique index users_email_uq on users (lower(email));

-- TABELAS

create table profiles (
    id uuid primary key references users (id),
    display_name text not null check (length(display_name) between 1 and 60),
    avatar_url text,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    deleted_at timestamptz,
    server_version bigint not null default 0
);

create table houses (
    id uuid primary key default gen_random_uuid(),
    name text not null,
    invite_code text not null unique,
    weekly_points_goal integer not null default 2000 check (weekly_points_goal > 0),
    created_by uuid not null references profiles (id),
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    deleted_at timestamptz,
    server_version bigint not null default 0
);

create table house_members (
    id uuid primary key default gen_random_uuid(),
    house_id uuid not null references houses (id),
    user_id uuid not null references profiles (id),
    role text not null check (role in ('owner', 'member')),
    joined_at timestamptz not null default now(),
    left_at timestamptz,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    deleted_at timestamptz,
    server_version bigint not null default 0,
    unique (house_id, user_id)
);

create table tasks (
    id uuid primary key,
    house_id uuid not null references houses (id),
    title text not null,
    assignee_member_id uuid references house_members (id),
    priority text not null default 'normal' check (priority in ('low', 'normal', 'high')),
    due_date date not null,
    points integer not null default 10 check (points >= 0),
    completed_at timestamptz,
    completed_by_member_id uuid references house_members (id),
    created_by_member_id uuid not null references house_members (id),
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    deleted_at timestamptz,
    server_version bigint not null default 0
);

create table expenses (
    id uuid primary key,
    house_id uuid not null references houses (id),
    description text not null,
    category text not null check (category in ('moradia', 'alimentacao', 'utilidades', 'outros')),
    amount_cents bigint not null check (amount_cents > 0),
    paid_by_member_id uuid not null references house_members (id),
    spent_on date not null,
    created_by_member_id uuid not null references house_members (id),
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    deleted_at timestamptz,
    server_version bigint not null default 0
);

create table expense_shares (
    id uuid primary key,                       -- determinístico: "share:<expenseId>:<memberId>"
    house_id uuid not null references houses (id),
    expense_id uuid not null references expenses (id),
    member_id uuid not null references house_members (id),
    share_cents bigint not null check (share_cents >= 0),
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    deleted_at timestamptz,
    server_version bigint not null default 0,
    unique (expense_id, member_id)
);

create table settlements (
    id uuid primary key,
    house_id uuid not null references houses (id),
    from_member_id uuid not null references house_members (id),
    to_member_id uuid not null references house_members (id),
    amount_cents bigint not null check (amount_cents > 0),
    settled_on date not null,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    deleted_at timestamptz,
    server_version bigint not null default 0,
    check (from_member_id <> to_member_id)
);

create table bills (
    id uuid primary key,
    house_id uuid not null references houses (id),
    name text not null,
    amount_cents bigint not null check (amount_cents > 0),
    due_date date not null,
    paid_at timestamptz,
    expense_id uuid references expenses (id),
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    deleted_at timestamptz,
    server_version bigint not null default 0
);

create table fund_transactions (
    id uuid primary key,
    house_id uuid not null references houses (id),
    member_id uuid not null references house_members (id),
    amount_cents bigint not null check (amount_cents <> 0),
    description text,
    occurred_on date not null,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    deleted_at timestamptz,
    server_version bigint not null default 0
);

create table pantry_items (
    id uuid primary key,
    house_id uuid not null references houses (id),
    name text not null,
    icon text,
    quantity numeric(10, 3) not null default 0 check (quantity >= 0),
    unit text not null check (unit in ('un', 'g', 'kg', 'ml', 'l', 'pacote')),
    min_quantity numeric(10, 3) not null default 0,
    expires_on date,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    deleted_at timestamptz,
    server_version bigint not null default 0
);

create table shopping_items (
    id uuid primary key,
    house_id uuid not null references houses (id),
    name text not null,
    pantry_item_id uuid references pantry_items (id),
    checked_at timestamptz,
    checked_by_member_id uuid references house_members (id),
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    deleted_at timestamptz,
    server_version bigint not null default 0
);

create table points_entries (
    id uuid primary key,                       -- determinístico: "<reason>:<source_id>"
    house_id uuid not null references houses (id),
    member_id uuid not null references house_members (id),
    points integer not null,
    reason text not null check (reason in ('task_completed', 'expense_paid', 'bill_paid')),
    source_id uuid not null,
    earned_at timestamptz not null,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now(),
    deleted_at timestamptz,
    server_version bigint not null default 0
);

-- TRIGGERS

do $$
declare t text;
begin
    foreach t in array array[
        'profiles', 'houses', 'house_members', 'tasks', 'expenses', 'expense_shares',
        'settlements', 'bills', 'fund_transactions', 'pantry_items', 'shopping_items',
        'points_entries'
    ] loop
        execute format(
            'create trigger %I before insert or update on %I
             for each row execute function set_sync_fields()', t || '_sync', t);
    end loop;

    foreach t in array array[
        'house_members', 'tasks', 'expenses', 'expense_shares', 'settlements', 'bills',
        'fund_transactions', 'pantry_items', 'shopping_items', 'points_entries'
    ] loop
        execute format('create index %I on %I (house_id, server_version)', t || '_pull_idx', t);
    end loop;
end $$;

-- FUNCOES

create or replace function sign_up(p_email text, p_password text, p_display_name text)
returns uuid
language plpgsql security definer set search_path = public, extensions as $$
declare v_user uuid;
begin
    if length(coalesce(p_password, '')) < 8 then
        raise exception 'weak_password';
    end if;
    if exists (select 1 from users where lower(email) = lower(trim(p_email))) then
        raise exception 'email_already_in_use';
    end if;

    insert into users (email, password_hash)
    values (lower(trim(p_email)), crypt(p_password, gen_salt('bf')))
    returning id into v_user;

    insert into profiles (id, display_name) values (v_user, trim(p_display_name));
    return v_user;
end $$;

create or replace function sign_in(p_email text, p_password text) returns uuid
language plpgsql security definer set search_path = public, extensions as $$
declare v_user uuid;
begin
    select id into v_user from users
     where lower(email) = lower(trim(p_email))
       and password_hash = crypt(p_password, password_hash);

    if v_user is null then
        raise exception 'invalid_credentials';
    end if;
    return v_user;
end $$;

create or replace function gen_invite_code() returns text
language plpgsql set search_path = public as $$
declare
    alphabet constant text := 'ABCDEFGHJKMNPQRSTUVWXYZ23456789';
    v_code text;
begin
    loop
        select string_agg(substr(alphabet, 1 + floor(random() * length(alphabet))::int, 1), '')
          into v_code
          from generate_series(1, 6);
        exit when not exists (select 1 from houses where invite_code = v_code);
    end loop;
    return v_code;
end $$;

create or replace function create_house(p_user_id uuid, p_name text, p_weekly_points_goal integer default 2000)
returns uuid
language plpgsql security definer set search_path = public as $$
declare v_house uuid;
begin
    if not exists (select 1 from profiles where id = p_user_id and deleted_at is null) then
        raise exception 'not_authenticated';
    end if;

    insert into houses (name, invite_code, weekly_points_goal, created_by)
    values (trim(p_name), gen_invite_code(), p_weekly_points_goal, p_user_id)
    returning id into v_house;

    insert into house_members (house_id, user_id, role) values (v_house, p_user_id, 'owner');
    return v_house;
end $$;

create or replace function preview_house(p_code text)
returns table (house_id uuid, house_name text, member_count int, owner_name text)
language sql stable security definer set search_path = public as $$
    select h.id, h.name,
           (select count(*)::int from house_members m
             where m.house_id = h.id and m.left_at is null and m.deleted_at is null),
           (select p.display_name from house_members m join profiles p on p.id = m.user_id
             where m.house_id = h.id and m.role = 'owner' limit 1)
      from houses h
     where h.invite_code = upper(trim(p_code)) and h.deleted_at is null;
$$;

create or replace function join_house(p_user_id uuid, p_code text) returns uuid
language plpgsql security definer set search_path = public as $$
declare v_house uuid;
begin
    if not exists (select 1 from profiles where id = p_user_id and deleted_at is null) then
        raise exception 'not_authenticated';
    end if;

    select id into v_house from houses
     where invite_code = upper(trim(p_code)) and deleted_at is null;
    if v_house is null then
        raise exception 'invalid_invite_code';
    end if;

    insert into house_members (house_id, user_id, role)
    values (v_house, p_user_id, 'member')
    on conflict (house_id, user_id) do update set left_at = null, deleted_at = null;

    return v_house;
end $$;

create or replace function regenerate_invite_code(p_user_id uuid, p_house uuid) returns text
language plpgsql security definer set search_path = public as $$
declare v_code text;
begin
    if not exists (
        select 1 from house_members
         where house_id = p_house and user_id = p_user_id and role = 'owner'
           and left_at is null and deleted_at is null
    ) then
        raise exception 'not_house_owner';
    end if;

    v_code := gen_invite_code();
    update houses set invite_code = v_code where id = p_house;
    return v_code;
end $$;

-- PAPEL DO APP

do $$
begin
    if not exists (select 1 from pg_roles where rolname = 'casajunta_app') then
        create role casajunta_app nologin;
    end if;
end $$;

grant usage on schema public to casajunta_app;

grant select, insert, update on
    profiles, tasks, expenses, expense_shares, settlements, bills,
    fund_transactions, pantry_items, shopping_items, points_entries
    to casajunta_app;
grant select on houses, house_members to casajunta_app;
grant update (name, weekly_points_goal, deleted_at) on houses to casajunta_app;

revoke execute on all functions in schema public from public;
grant execute on function
    sign_up(text, text, text), sign_in(text, text),
    create_house(uuid, text, integer), preview_house(text),
    join_house(uuid, text), regenerate_invite_code(uuid, uuid)
    to casajunta_app;
