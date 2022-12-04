(ns day-1.core
  (:require [clojure.string :as str]))

(defn- paragraph-lines [s]
  (for [paragraph (str/split s #"\n\n")]
    (str/split paragraph #"\n")))

(defn- paragraph-lines-total [s]
  (for [paragraph-line (paragraph-lines s)]
    (transduce (map parse-long) + paragraph-line)))

(defn highest-total-calories
  "Gets the elf(s) with the highest amount of calories.
  If no n is specified, it returns the elf with the highest amount of calories."
  ([s]
   (->> (paragraph-lines-total s)
        (apply max)))
  ([s n]
   (->> (paragraph-lines-total s)
        (sort >)
        (transduce (take n) + 0))))
