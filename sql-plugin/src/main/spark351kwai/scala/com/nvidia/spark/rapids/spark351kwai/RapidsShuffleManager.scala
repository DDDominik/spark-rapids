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

/*** spark-rapids-shim-json-lines
{"spark": "351kwai"}
spark-rapids-shim-json-lines ***/
package com.nvidia.spark.rapids.spark351kwai

import org.apache.spark.{ShuffleDependency, SparkConf}
import org.apache.spark.shuffle._
import org.apache.spark.sql.rapids.ProxyRapidsShuffleInternalManagerBase

/**
 * A shuffle manager optimized for the RAPIDS Plugin for Apache Spark (Kwai variant).
 * 
 * Note: Kwai's internal Spark version has a modified ShuffleManager.registerShuffle API
 * that includes an additional numMaps parameter:
 *   registerShuffle[K, V, C](shuffleId: Int, numMaps: Int, dependency: ShuffleDependency[K, V, C])
 * 
 * We override this method to accept the extra parameter but currently ignore it since
 * the internal implementation doesn't require it.
 */
sealed class RapidsShuffleManager(
    conf: SparkConf,
    isDriver: Boolean
) extends ProxyRapidsShuffleInternalManagerBase(conf, isDriver) {

  /**
   * Kwai internal version has an extra numMaps parameter.
   * We accept it but delegate to the standard 2-parameter version in the parent class.
   */
  override def registerShuffle[K, V, C](
      shuffleId: Int,
      numMaps: Int,
      dependency: ShuffleDependency[K, V, C]): ShuffleHandle = {
    // numMaps is just an optimization hint for Kwai's implementation.
    // We can safely ignore it and use the standard registerShuffle method.
    super.registerShuffle(shuffleId, numMaps, dependency)
  }
}
