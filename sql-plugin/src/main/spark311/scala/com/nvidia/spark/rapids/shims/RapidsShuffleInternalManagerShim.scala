/*** spark-rapids-shim-json-lines
{"spark": "311"}
{"spark": "312"}
{"spark": "313"}
{"spark": "320"}
{"spark": "321"}
{"spark": "321cdh"}
{"spark": "322"}
{"spark": "323"}
{"spark": "324"}
{"spark": "330"}
{"spark": "330cdh"}
{"spark": "330db"}
{"spark": "331"}
{"spark": "332"}
{"spark": "332cdh"}
{"spark": "332db"}
{"spark": "333"}
{"spark": "334"}
{"spark": "340"}
{"spark": "341"}
{"spark": "341db"}
{"spark": "342"}
{"spark": "350"}
{"spark": "351"}
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
 * Shim for standard Apache Spark which has the standard registerShuffle signature.
 */
object RapidsShuffleInternalManagerShim {
  /**
   * Register shuffle with the wrapped shuffle manager.
   * Standard Spark uses 2-parameter signature.
   */
  def registerShuffleWithWrapped[K, V, C](
      wrapped: ShuffleManager,
      shuffleId: Int,
      dependency: ShuffleDependency[K, V, C]): ShuffleHandle = {
    // Standard Spark registerShuffle signature:
    // def registerShuffle[K, V, C](
    //   shuffleId: Int, dependency: ShuffleDependency[K, V, C])
    wrapped.registerShuffle(shuffleId, dependency)
  }
}
