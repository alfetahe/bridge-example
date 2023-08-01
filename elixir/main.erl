-module(main).
-export([start/0, loop/0]).

start() ->
    register(handler, spawn(?MODULE, loop, [])). 

loop() ->
    {Msg, Pid} = receive
        {total_atoms, From} -> 
            {{atoms, erlang:system_info(atom_count)}, From};
        {total_processes, From} -> 
            {#{total_processes => erlang:system_info(process_count)}, From}
    end,
    send_data(Msg, Pid),
    increment(),
    loop().

increment() ->
    spawn(?MODULE, loop, []),   
    erlang:list_to_atom(erlang:float_to_list(rand:uniform())). 
        
send_data(Data, Pid) ->
    Pid ! Data.