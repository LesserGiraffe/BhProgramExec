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

package net.seapanda.bunnyhop.bhprogram.common.message;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import net.seapanda.bunnyhop.bhprogram.common.BhNodeInstanceId;

/**
 * BhProgam 実行時に発生した例外情報を保持するクラス.
 *
 * @author K.Koike
 */
public class BhProgramException extends RuntimeException implements BhProgramMessage {

  private final Deque<BhNodeInstanceId> callStack;
  private final String scriptEngineMsg;
  private final int id;

  /**
   * コンストラクタ.
   *
   * @param callStack 例外が発生した時のコールスタック
   * @param msg 例外メッセージ
   */
  public BhProgramException(Collection<BhNodeInstanceId> callStack, String msg) {
    super(msg);
    this.callStack = new LinkedList<>(callStack);
    scriptEngineMsg = "";
    id = genId();
  }

  /**
   * コンストラクタ.
   *
   * @param callStack 例外が発生した時のコールスタック
   * @param msg 例外メッセージ
   * @param scriptEngineMsg BhProgram の実行エンジンから返されたエラーメッセージ
   */
  public BhProgramException(
      Collection<BhNodeInstanceId> callStack, String msg, String scriptEngineMsg) {
    super(msg);
    this.callStack = new LinkedList<>(callStack);
    this.scriptEngineMsg = scriptEngineMsg;
    id = genId();
  }

  /** BhProgram の実行エンジンから返されたエラーメッセージを取得する. */
  public String getScriptEngineMsg() {
    return scriptEngineMsg;
  }

  /** 例外が発生した時のコールスタックを取得する. */
  public Deque<BhNodeInstanceId> getCallStack() {
    return new LinkedList<>(callStack);
  }

  @Override
  public int getId() {
    return id;
  }
}
