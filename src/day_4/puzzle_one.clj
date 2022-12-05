(ns day-4.puzzle-one
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

(defn fully-contained? [xs]
  "Predicate for finding out if the range is fully contained."
  (->> (map set xs)
       (sort-by count)
       (apply set/subset?)))

(defn camp-cleanup [s]
  (->> (split-sections s)
       (map section-range)
       (map fully-contained?)
       (filter true?)
       (count)))
