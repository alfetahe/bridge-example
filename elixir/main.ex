defmodule Main do
  # The main API function to start the server and register the process
  # with the name `handler`.
  def start() do
    Process.register(Process.spawn(__MODULE__, :loop, [], []), :handler)
  end

  # The loop function which waits for incoming messages, handles them
  # and recursivly calls itself again.
  def loop() do
    {msg, from} = receive do
      {:total_atoms, from} ->
        {{:atoms, :erlang.system_info(:atom_count)}, from};
      {:total_processes, from} ->
        {%{total_processes: :erlang.system_info(:process_count)}, from}
    end

    send_data(msg, from)
    increment()
    loop()
  end

  # Function to increment the atom count by spawning a new process.
  # this will be used for demonstration purposes to show how the data is updated.
  def increment() do
    Process.spawn(__MODULE__, :loop, [], [])
    String.to_atom(Integer.to_string(Enum.random(1..100000)))
  end

  # Respond to the client with the data.
  defp send_data(data, pid) do
    Process.send(pid, data, [])
  end
end

# Start the server.
Main.start()
