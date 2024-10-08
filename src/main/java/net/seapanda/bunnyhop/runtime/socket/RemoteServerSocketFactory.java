/*
 * Copyright 2017 K.Koike
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.seapanda.bunnyhop.runtime.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.server.RMIServerSocketFactory;

/**
 * リモート通信用のソケットを作成するファクトリ.
 *
 * @author K.Koike
 */
public class RemoteServerSocketFactory implements RMIServerSocketFactory {

  private int localPort;
  private int id;

  /**
   * コンストラクタ.
   *
   * @param id オブジェクトを識別するための ID
   */
  public RemoteServerSocketFactory(int id) {
    this.id = id;
  }

  @Override
  public ServerSocket createServerSocket(int port) throws IOException {
    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket(port);
    } catch (IOException e) {
      throw new IOException();
    }
    localPort = serverSocket.getLocalPort();
    return serverSocket;
  }

  @Override
  public int hashCode() {
    return id;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    return (getClass() == obj.getClass()) && (id == ((RemoteServerSocketFactory) obj).id);
  }

  public int getLocalPort() {
    return localPort;
  }
}
