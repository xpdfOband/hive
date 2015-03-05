/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.hive.llap.io.api.cache;

import java.nio.ByteBuffer;

import org.apache.hadoop.metrics2.MetricsSource;

public abstract class LlapMemoryBuffer {
  protected LlapMemoryBuffer() {
  }
  protected void initialize(ByteBuffer byteBuffer, int offset, int length, MetricsSource metrics) {
    this.byteBuffer = byteBuffer.slice();
    this.byteBuffer.position(offset);
    this.byteBuffer.limit(offset + length);
    this.metrics = metrics;
  }
  /** Note - position/limit of this should NOT be modified after it's in cache.
      We could add a wrapper to enforce that, but for now it's shared and should be duplicated. */
  public ByteBuffer byteBuffer;
  public MetricsSource metrics;
}