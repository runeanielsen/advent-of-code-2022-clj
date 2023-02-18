(ns day-7.puzzle-one)

(defn- parse-change-directory [text]
  "Parses change directory command(cd).
  If no cd command is found, nil is returned."
  (if-some [[_ input] (re-matches #"^\$ cd (\/[a-z-A-Z\.\-\_]*)$" text)]
    {:type :cd
     :input input}))

(defn- parse-ls [text]
  "Parses ls command. If no command is found nil is returned."
  (if ((complement nil?) (re-find #"^\$ ls$" text))
    {:type :ls}))

(defn- parse-directory [text]
  "Parses directory output from the ls command."
  (if-some [[_ dir-name] (re-matches #"^dir (.+)$" text)]
    {:type :dir
     :name dir-name}))

(defn parse-command [text]
  "Parses the text to a command representation."
  (->> [(parse-change-directory text)
        (parse-ls text)
        (parse-directory text)]
       (filter (completing nil?))
       (first)))
