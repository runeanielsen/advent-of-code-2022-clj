(ns day-1.tests
  (:require [day-1.core :refer [highest-total-calories]]
            [clojure.test :refer [deftest is]]
            [clojure.java.io :as io]))

(deftest highest-total-calories-returns-highest-total
  (let [input (slurp (io/resource "day_1/calorie-list.txt"))]
    (is (= (highest-total-calories input) 71502))))
