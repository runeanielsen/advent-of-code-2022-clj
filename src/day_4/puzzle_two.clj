(ns day-4.puzzle-two
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn split-sections [s]
  "Splits the groups into sections."
  (str/split-lines s))

(defn section-range [s]
  "Make section into ranges."
  (for [xs (map #(str/split % #"-") (str/split s #","))]
    (let [[rs re] (map parse-long xs)]
      (range rs (inc re)))))

(defn overlaps? [xs]
  "Predicate checking if anything overlaps in ranges."
  (->> (map set xs)
       (apply set/intersection)
       (count)
       (< 0)))

(defn camp-cleanup [s]
  (->> (split-sections s)
       (map section-range)
       (map overlaps?)
       (filter true?)
       (count)))
