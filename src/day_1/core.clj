(ns day-1.core
  (:require [clojure.string :as str]))

(defn- paragraph-lines [s]
  (for [paragraph (str/split s #"\n\n")]
    (str/split paragraph #"\n")))

(defn highest-total-calories [s n]
  (->> (for [elf-calories (paragraph-lines s)]
         (transduce (map parse-long) + elf-calories))
       (sort >)
       (transduce (take n) + 0)))
