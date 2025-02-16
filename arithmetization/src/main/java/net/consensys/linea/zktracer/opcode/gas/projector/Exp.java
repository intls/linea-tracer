/*
 * Copyright Consensys Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package net.consensys.linea.zktracer.opcode.gas.projector;

import net.consensys.linea.zktracer.module.constants.GlobalConstants;
import org.hyperledger.besu.evm.frame.MessageFrame;

public final class Exp extends GasProjection {
  private int exponentByteSize = 0;

  public Exp(MessageFrame frame) {
    if (frame.stackSize() > 1) {
      final int bitLength = frame.getStackItem(1).bitLength();
      this.exponentByteSize = (bitLength + 7) / 8;
    }
  }

  @Override
  public long staticGas() {
    return GlobalConstants.GAS_CONST_G_EXP;
  }

  @Override
  public long expGas() {
    return linearCost(GlobalConstants.GAS_CONST_G_EXP_BYTE, this.exponentByteSize, 1);
  }
}
