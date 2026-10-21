-- LOGIN DO APP
do $$
begin
    if not exists (select 1 from pg_roles where rolname = '${appDbUser}') then
        execute format('create role %I login password %L in role casajunta_app',
                       '${appDbUser}', '${appDbPassword}');
    else
        execute format('alter role %I login password %L', '${appDbUser}', '${appDbPassword}');
        execute format('grant casajunta_app to %I', '${appDbUser}');
    end if;
end $$;
