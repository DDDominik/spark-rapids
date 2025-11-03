/*** spark-rapids-shim-json-lines
{"spark": "351kwai"}
spark-rapids-shim-json-lines ***/
/*
 * Copyright (c) 2024, NVIDIA CORPORATION.
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

package com.nvidia.spark.rapids.shims

import org.apache.spark.ShuffleDependency
import org.apache.spark.shuffle.{ShuffleHandle, ShuffleManager}

/**
 * Shim for Kuaishou Spark 3.5.1 which has a different registerShuffle signature
 * that includes numMaps parameter.
 */
object RapidsShuffleInternalManagerShim {
  /**
   * Register shuffle with the wrapped shuffle manager.
   * Kuaishou Spark 3.5.1 requires numMaps parameter.
   */
  def registerShuffleWithWrapped[K, V, C](
      wrapped: ShuffleManager,
      shuffleId: Int,
      numMaps: Int,
      dependency: ShuffleDependency[K, V, C]): ShuffleHandle = {
    // Kuaishou Spark 3.5.1 registerShuffle signature:
    // def registerShuffle[K, V, C](
    //   shuffleId: Int, numMaps: Int, dependency: ShuffleDependency[K, V, C])
    wrapped.registerShuffle(shuffleId, numMaps, dependency)
  }
}
