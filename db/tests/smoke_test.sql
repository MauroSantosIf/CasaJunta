-- SMOKE

do $$
declare
    v_user uuid;
    v_house uuid;
    v_code text;
    v_v1 bigint;
    v_v2 bigint;
    v_row record;
begin
    -- 1. sign_in correto
    v_user := sign_in('Camila@CasaJunta.dev ', 'casajunta123');
    assert v_user = 'a0000000-0000-4000-8000-000000000001', 'sign_in deveria devolver a Camila';

    -- 2. sign_in com senha errada
    begin
        perform sign_in('camila@casajunta.dev', 'errada123');
        raise exception 'sign_in deveria falhar';
    exception when raise_exception then
        assert sqlerrm = 'invalid_credentials', 'esperado invalid_credentials, veio ' || sqlerrm;
    end;

    -- 3. sign_up duplicado e senha fraca
    begin
        perform sign_up('camila@casajunta.dev', 'outrasenha1', 'Camila 2');
        raise exception 'sign_up deveria falhar';
    exception when raise_exception then
        assert sqlerrm = 'email_already_in_use', 'esperado email_already_in_use, veio ' || sqlerrm;
    end;
    begin
        perform sign_up('nova@casajunta.dev', '123', 'Nova');
        raise exception 'sign_up deveria falhar';
    exception when raise_exception then
        assert sqlerrm = 'weak_password', 'esperado weak_password, veio ' || sqlerrm;
    end;

    -- 4. sign_up novo + criar casa + código válido
    v_user := sign_up('nova@casajunta.dev', 'senhaforte1', 'Nova');
    assert exists (select 1 from profiles where id = v_user), 'sign_up deveria criar o profile';
    v_house := create_house(v_user, 'Casa Teste', 1500);
    select invite_code into v_code from houses where id = v_house;
    assert v_code ~ '^[ABCDEFGHJKMNPQRSTUVWXYZ23456789]{6}$', 'código fora do alfabeto: ' || v_code;
    assert (select role from house_members where house_id = v_house and user_id = v_user) = 'owner',
           'criador deveria ser owner';

    -- 5. preview e join
    select * into v_row from preview_house(' k7q2xm ');
    assert v_row.house_name = 'República Mandacaru' and v_row.member_count = 4 and v_row.owner_name = 'Rafael',
           'preview_house com dados inesperados';
    begin
        perform join_house(v_user, 'ZZZZZZ');
        raise exception 'join_house deveria falhar';
    exception when raise_exception then
        assert sqlerrm = 'invalid_invite_code', 'esperado invalid_invite_code, veio ' || sqlerrm;
    end;
    assert join_house(v_user, 'K7Q2XM') = 'b0000000-0000-4000-8000-000000000001', 'join_house deveria entrar';

    -- 6. regenerar código: só o owner
    begin
        perform regenerate_invite_code('a0000000-0000-4000-8000-000000000001', 'b0000000-0000-4000-8000-000000000001');
        raise exception 'regenerate deveria falhar';
    exception when raise_exception then
        assert sqlerrm = 'not_house_owner', 'esperado not_house_owner, veio ' || sqlerrm;
    end;
    assert length(regenerate_invite_code('a0000000-0000-4000-8000-000000000002', 'b0000000-0000-4000-8000-000000000001')) = 6,
           'owner deveria gerar código';

    -- 7. trigger de sync avança server_version
    select server_version into v_v1 from tasks where id = 'd0000000-0000-4000-8000-000000000001';
    update tasks set title = 'Lavar a louça!' where id = 'd0000000-0000-4000-8000-000000000001'
    returning server_version into v_v2;
    assert v_v2 > v_v1, 'server_version deveria aumentar';

    -- 8. seed: partes somam o valor e o id determinístico bate com o Kotlin
    assert not exists (
        select 1 from expenses e
         where e.amount_cents <> (select sum(share_cents) from expense_shares s where s.expense_id = e.id)
    ), 'soma das partes diferente do valor';
    assert exists (select 1 from points_entries where id = '59370573-a8c4-377d-8a89-e680604c0c45'),
           'id determinístico do seed não bate com UUID.nameUUIDFromBytes';
end $$;

-- 9. Privilégios do papel do app
set local role casajunta_app;
do $$
begin
    begin
        delete from tasks where id = 'd0000000-0000-4000-8000-000000000001';
        raise exception 'app não deveria poder DELETE';
    exception when insufficient_privilege then null;
    end;
    begin
        perform 1 from users limit 1;
        raise exception 'app não deveria ler users';
    exception when insufficient_privilege then null;
    end;
    begin
        insert into houses (name, invite_code, created_by)
        values ('x', 'XXXXXX', 'a0000000-0000-4000-8000-000000000001');
        raise exception 'app não deveria inserir casa direto';
    exception when insufficient_privilege then null;
    end;
    assert (select count(*) from tasks) >= 8, 'app deveria ler tarefas';
    update tasks set title = 'Varrer a sala' where id = 'd0000000-0000-4000-8000-000000000002';
    assert sign_in('rafael@casajunta.dev', 'casajunta123') = 'a0000000-0000-4000-8000-000000000002',
           'app deveria executar sign_in';
end $$;
reset role;

select 'OK — todos os testes do banco passaram' as resultado;
rollback;
