-- SEED LOCAL
-- Senha: casajunta123. Ids deterministicos: UUID v3 de nameUUIDFromBytes em UTF-8.

set search_path = public, extensions;

create or replace function pg_temp.uuid_v3(t text) returns uuid language sql immutable as $$
    select (substr(h, 1, 12) || '3' || substr(h, 14, 3)
            || to_hex((('x' || substr(h, 17, 1))::bit(4)::int & 3) | 8)
            || substr(h, 18, 15))::uuid
      from (select md5(convert_to(t, 'UTF8')) as h) s;
$$;

-- CONTAS
insert into users (id, email, password_hash)
select u.id, u.email, crypt('casajunta123', gen_salt('bf'))
  from (values
        ('a0000000-0000-4000-8000-000000000001'::uuid, 'camila@casajunta.dev'),
        ('a0000000-0000-4000-8000-000000000002'::uuid, 'rafael@casajunta.dev'),
        ('a0000000-0000-4000-8000-000000000003'::uuid, 'juliana@casajunta.dev'),
        ('a0000000-0000-4000-8000-000000000004'::uuid, 'pedro@casajunta.dev')) as u(id, email)
on conflict (id) do nothing;

insert into profiles (id, display_name) values
    ('a0000000-0000-4000-8000-000000000001', 'Camila'),
    ('a0000000-0000-4000-8000-000000000002', 'Rafael'),
    ('a0000000-0000-4000-8000-000000000003', 'Juliana'),
    ('a0000000-0000-4000-8000-000000000004', 'Pedro')
on conflict (id) do nothing;

insert into houses (id, name, invite_code, weekly_points_goal, created_by) values
    ('b0000000-0000-4000-8000-000000000001', 'República Mandacaru', 'K7Q2XM', 2000,
     'a0000000-0000-4000-8000-000000000002')
on conflict (id) do nothing;

insert into house_members (id, house_id, user_id, role, joined_at) values
    ('c0000000-0000-4000-8000-000000000002', 'b0000000-0000-4000-8000-000000000001', 'a0000000-0000-4000-8000-000000000002', 'owner',  now() - interval '31 days'),
    ('c0000000-0000-4000-8000-000000000001', 'b0000000-0000-4000-8000-000000000001', 'a0000000-0000-4000-8000-000000000001', 'member', now() - interval '30 days'),
    ('c0000000-0000-4000-8000-000000000003', 'b0000000-0000-4000-8000-000000000001', 'a0000000-0000-4000-8000-000000000003', 'member', now() - interval '29 days'),
    ('c0000000-0000-4000-8000-000000000004', 'b0000000-0000-4000-8000-000000000001', 'a0000000-0000-4000-8000-000000000004', 'member', now() - interval '28 days')
on conflict (id) do nothing;

-- TAREFAS
insert into tasks (id, house_id, title, assignee_member_id, priority, due_date, points,
                   completed_at, completed_by_member_id, created_by_member_id)
select t.id, 'b0000000-0000-4000-8000-000000000001', t.title, t.member, t.priority,
       current_date + t.due_offset, 10,
       case when t.done_offset is null then null else now() + make_interval(days => t.done_offset) end,
       case when t.done_offset is null then null else t.member end,
       'c0000000-0000-4000-8000-000000000002'
  from (values
    ('d0000000-0000-4000-8000-000000000001'::uuid, 'Lavar a louça',      'c0000000-0000-4000-8000-000000000001'::uuid, 'normal', 0,  null::int),
    ('d0000000-0000-4000-8000-000000000002'::uuid, 'Varrer a sala',      'c0000000-0000-4000-8000-000000000002'::uuid, 'high',   0,  null),
    ('d0000000-0000-4000-8000-000000000003'::uuid, 'Limpar o banheiro',  'c0000000-0000-4000-8000-000000000003'::uuid, 'normal', 0,  null),
    ('d0000000-0000-4000-8000-000000000004'::uuid, 'Tirar o lixo',       'c0000000-0000-4000-8000-000000000004'::uuid, 'low',    0,  null),
    ('d0000000-0000-4000-8000-000000000005'::uuid, 'Regar as plantas',   'c0000000-0000-4000-8000-000000000001'::uuid, 'normal', -1, -1),
    ('d0000000-0000-4000-8000-000000000006'::uuid, 'Lavar roupa',        'c0000000-0000-4000-8000-000000000002'::uuid, 'normal', -2, -2),
    ('d0000000-0000-4000-8000-000000000007'::uuid, 'Limpar a geladeira', 'c0000000-0000-4000-8000-000000000003'::uuid, 'high',   -3, -3),
    ('d0000000-0000-4000-8000-000000000008'::uuid, 'Trocar a lâmpada',   'c0000000-0000-4000-8000-000000000004'::uuid, 'low',    -2, -2)
  ) as t(id, title, member, priority, due_offset, done_offset)
on conflict (id) do nothing;

insert into points_entries (id, house_id, member_id, points, reason, source_id, earned_at)
select pg_temp.uuid_v3('task_completed:' || t.id), t.house_id, t.completed_by_member_id,
       t.points, 'task_completed', t.id, t.completed_at
  from tasks t
 where t.completed_at is not null
   and t.house_id = 'b0000000-0000-4000-8000-000000000001'
on conflict (id) do nothing;

-- FINANCAS
insert into expenses (id, house_id, description, category, amount_cents, paid_by_member_id,
                      spent_on, created_by_member_id)
select e.id, 'b0000000-0000-4000-8000-000000000001', e.description, e.category, e.amount,
       e.member, current_date - e.days_ago, e.member
  from (values
    ('e0000000-0000-4000-8000-000000000001'::uuid, 'Aluguel (parte)',     'moradia',     52000, 'c0000000-0000-4000-8000-000000000002'::uuid, 10),
    ('e0000000-0000-4000-8000-000000000002'::uuid, 'Mercado do mês',      'alimentacao', 38050, 'c0000000-0000-4000-8000-000000000001'::uuid, 8),
    ('e0000000-0000-4000-8000-000000000003'::uuid, 'Conta de luz',        'utilidades',  24840, 'c0000000-0000-4000-8000-000000000004'::uuid, 5),
    ('e0000000-0000-4000-8000-000000000004'::uuid, 'Produtos de limpeza', 'outros',      10000, 'c0000000-0000-4000-8000-000000000003'::uuid, 2)
  ) as e(id, description, category, amount, member, days_ago)
on conflict (id) do nothing;

-- Divisão igual; centavos que sobram vão para os primeiros por (joined_at, id).
insert into expense_shares (id, house_id, expense_id, member_id, share_cents)
select pg_temp.uuid_v3('share:' || e.id || ':' || m.id), e.house_id, e.id, m.id,
       e.amount_cents / m.total + case when m.rn <= e.amount_cents % m.total then 1 else 0 end
  from expenses e
  join (select id, house_id,
               row_number() over (partition by house_id order by joined_at, id) as rn,
               count(*) over (partition by house_id) as total
          from house_members where left_at is null and deleted_at is null) m
    on m.house_id = e.house_id
 where e.house_id = 'b0000000-0000-4000-8000-000000000001'
on conflict (id) do nothing;

insert into bills (id, house_id, name, amount_cents, due_date) values
    ('f0000000-0000-4000-8000-000000000001', 'b0000000-0000-4000-8000-000000000001', 'Internet', 12000, current_date + 5),
    ('f0000000-0000-4000-8000-000000000002', 'b0000000-0000-4000-8000-000000000001', 'Água',     20050, current_date + 5)
on conflict (id) do nothing;

insert into fund_transactions (id, house_id, member_id, amount_cents, description, occurred_on) values
    ('f1000000-0000-4000-8000-000000000001', 'b0000000-0000-4000-8000-000000000001', 'c0000000-0000-4000-8000-000000000001',  15000, 'Depósito no caixa', current_date - 12),
    ('f1000000-0000-4000-8000-000000000002', 'b0000000-0000-4000-8000-000000000001', 'c0000000-0000-4000-8000-000000000002',  15000, 'Depósito no caixa', current_date - 12),
    ('f1000000-0000-4000-8000-000000000003', 'b0000000-0000-4000-8000-000000000001', 'c0000000-0000-4000-8000-000000000003',  15000, 'Depósito no caixa', current_date - 12),
    ('f1000000-0000-4000-8000-000000000004', 'b0000000-0000-4000-8000-000000000001', 'c0000000-0000-4000-8000-000000000004',  15000, 'Depósito no caixa', current_date - 12),
    ('f1000000-0000-4000-8000-000000000005', 'b0000000-0000-4000-8000-000000000001', 'c0000000-0000-4000-8000-000000000002', -12770, 'Botijão de gás',    current_date - 3)
on conflict (id) do nothing;

-- DESPENSA
insert into pantry_items (id, house_id, name, icon, quantity, unit, min_quantity, expires_on) values
    ('f2000000-0000-4000-8000-000000000001', 'b0000000-0000-4000-8000-000000000001', 'Arroz',    'rice_bowl',    1,   'kg',     0.5, null),
    ('f2000000-0000-4000-8000-000000000002', 'b0000000-0000-4000-8000-000000000001', 'Feijão',   'grain',        0.5, 'kg',     1,   null),
    ('f2000000-0000-4000-8000-000000000003', 'b0000000-0000-4000-8000-000000000001', 'Macarrão', 'ramen_dining', 2,   'pacote', 1,   null),
    ('f2000000-0000-4000-8000-000000000004', 'b0000000-0000-4000-8000-000000000001', 'Leite',    'local_drink',  2,   'un',     1,   current_date + 3),
    ('f2000000-0000-4000-8000-000000000005', 'b0000000-0000-4000-8000-000000000001', 'Ovos',     'egg',          6,   'un',     12,  null),
    ('f2000000-0000-4000-8000-000000000006', 'b0000000-0000-4000-8000-000000000001', 'Café',     'coffee',       250, 'g',      100, null)
on conflict (id) do nothing;

insert into shopping_items (id, house_id, name, checked_at, checked_by_member_id) values
    ('f3000000-0000-4000-8000-000000000001', 'b0000000-0000-4000-8000-000000000001', 'Detergente',      now(), 'c0000000-0000-4000-8000-000000000001'),
    ('f3000000-0000-4000-8000-000000000002', 'b0000000-0000-4000-8000-000000000001', 'Papel higiênico', null,  null),
    ('f3000000-0000-4000-8000-000000000003', 'b0000000-0000-4000-8000-000000000001', 'Tomate',          null,  null),
    ('f3000000-0000-4000-8000-000000000004', 'b0000000-0000-4000-8000-000000000001', 'Cebola',          null,  null)
on conflict (id) do nothing;

reset search_path;
