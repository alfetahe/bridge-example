# Java and Elixir communication example using Jinterface

## Docker setup
1. Build the image `docker-compose build`
2. Start the container `docker-compose up -d`

## Elixir
1. Run the file and start listening for incoming messages using `iex --sname beam_node --cookie cookie  main.ex`

## Java
1. Compile:  `javac java/src/java_node/*.java -d java/out/ -classpath ".:/usr/local/otp/lib/erlang/lib/jinterface-1.13.2/priv/OtpErlang.jar"`
2. Execute the Main class: `java -classpath "java/out:/usr/local/otp/lib/erlang/lib/jinterface-1.13.2/priv/OtpErlang.jar" java_node.Main`

## Bonus
## Erlang
1. Open terminal and compile the file using `erlc main.erl`
2. Run the file and start listening for incoming messages using `erl -sname beam_node -setcookie cookie -eval "main:start()"`