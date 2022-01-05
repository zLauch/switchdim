global 

__config() -> {
    'stay_loaded' -> true,
    'commands' -> {
        'End' -> _() -> _gotoEnd();,
        'Overworld' -> _() ->_gotoOverworld();,
        'Nether' -> _() ->_gotoNether();
    }
};

_gotoEnd()->(
    plr = player();
    if
    (query(plr,'gamemode_id')==3,
        (
            run('execute in the_end run tp '+plr+' 0 100 0');
        ),
        _error();
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
            _error2();
        );,
        _error();
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
            _error2();
        );,
        _error();
    );
);

_error()->(
    plr = player();
    run('tellraw '+plr+' ["",{"text":"You can only run this command when you are in spectator mode","color":"red"}]');
);

_error2()->(
    plr = player();
    run('tellraw '+plr+' ["",{"text":"You can not switch to the Overworld or the Nether from the End.","color":"red"}]');
);
