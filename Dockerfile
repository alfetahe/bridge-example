FROM openjdk:11-jdk

RUN apt-get update

# Install build tools
RUN apt-get install -y git build-essential libncurses5-dev

# Install Erlang
RUN git clone https://github.com/erlang/otp.git /usr/local/src/otp
WORKDIR /usr/local/src/otp
RUN git checkout maint-25
RUN ./otp_build autoconf
RUN ./configure --prefix=/usr/local/otp
RUN make -j$(nproc)
RUN make install
ENV PATH="/usr/local/otp/bin:${PATH}"

# Install Elixir
RUN git clone https://github.com/elixir-lang/elixir.git /usr/local/src/elixir
WORKDIR /usr/local/src/elixir
RUN git checkout v1.15
RUN make clean compile
ENV PATH="/usr/local/src/elixir/bin:${PATH}"

WORKDIR /app