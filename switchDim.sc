gx = 0;
gy = 0;
gz = 0;
gdim = "";

__config() -> {
    'stay_loaded' -> true,
    'commands' -> {
        'End' -> _() -> _gotoEnd();,
        'Overworld' -> _() ->_gotoOverworld();,
        'Nether' -> _() ->_gotoNether();
        'returnFromEnd' -> _() -> _returnEnd()
    }
};

_gotoEnd()->(
    plr = player();
    if
    (query(plr,'gamemode_id')==3,
        (
            gx = round(query(plr, x));
            gy = round(query(plr, y));
            gy = round(query(plr, z));
            gdim = round(query(plr, dimension));

            run('execute in the_end run tp '+plr+' 0 100 0');
        ),
        _error(0);
    );
);

_returnEnd()->(
    plr = player();
    //gamemode test.
    if
    (query(plr, 'dimension')=='the_End',
        (
            run('execute in '+gdim+' run tp '+plr+' '+gx+' '+gy+' '+gz+'');
            gx = round(query(plr, x));
            gy = round(query(plr, y));
            gz = round(query(plr, z));
            gdim = round(query(plr, dimension));
        ),
        _error(1)
    );
);

_gotoOverworld()->(
    plr = player();
    if
    (query(plr,'gamemode_id')==3,
        if
        (query(plr, 'dimension')=='the_nether',
            (
                run('execute in overworld run tp '+plr+' '+round(query(plr,'x')/8)+' '+round(query(plr,'y'))+' '+round(query(plr,'z')/8)+'');
            ),
            _error(2);
        );,
        _error(0);
    );
);

_gotoNether()->(
    plr = player();
    if
    (query(plr,'gamemode_id')==3,
        if
        (query(plr, 'dimension')=='overworld',
            (
                run('execute in the_nether run tp '+plr+' '+round(query(plr,'x')*8)+' '+round(query(plr,'y'))+' '+round(query(plr,'z')*8)+'');
            ),
            _error(2);
        );,
        _error(0);
    );
);

_error(m)->(
    plr = player();
    if
    (m == 0,
        (
            run('tellraw '+plr+' ["",{"text":"ERROR: You can only run this command when you are in spectator mode.","color":"red"}]');
        ),
        m == 1,
        (
            run('tellraw '+plr+' ["",{"text":"ERROR: You can only exit the end if you are in the End.","color":"red"}]');
        ),
        m === 2,
        (
            run('tellraw '+plr+' ["",{"text":"ERROR: You can go there from this Dimension","color":"red"}]');
        ),
        run('tellraw '+plr+' ["",{"text":"ERROR: Unknown.","color":"red"}]');
    );
);
