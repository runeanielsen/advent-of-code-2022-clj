(ns day-1
  (:require [clojure.test :refer [deftest is]]
            [clojure.string :as str]
            [clojure.java.io :as io]))

(defn parse-calorie-list [xs]
  (->> (str/split xs #"\n\n")
       (map #(str/split % #"\n"))
       (map (fn [xs] (map #(Integer/parseInt %) xs)))))

(defn highest-total-calories [calorie-list]
  (->> calorie-list
       (parse-calorie-list)
       (mapv #(apply + %))
       (sort)
       (last)))

(deftest example-one
  (let [x "1000
2000
3000

4000

5000
6000

7000
8000
9000

10000"]
    (is (= (highest-total-calories x) 24000))))

(deftest user-specfic-input-test
  (let [input (slurp (io/resource "day_1/calorie-list.txt"))]
    (is (= (highest-total-calories input) 71502))))
