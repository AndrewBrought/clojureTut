(ns tutorial.fundamentals
  (:require
    [clojure.string :as str]))
; Start REPL with cmd + r
; Once the REPL is started we can use shift + cmd + p to
; run a line in the REPL box - BUT - first
; we need to run the doc-name line first

; LISTS


(println "Hello"
         (+ 2 3)
         )

(def names (str/split-lines (slurp "/usr/share/dict/propernames")))

; Practicing Simple Funtions

; Here we have a function that will join the characters with
; a '-' and reverse the order ** to use with REPL
; we first have to run the line (shift + cmd + p) then we
; can call the function in the REPL window
(def mangle
  (fn [string]
    (str/join "-" (str/reverse string))))

; Another way to write this function is to shorten by including
; defn
(defn mangle2
  [string]
  (str/join "*" (str/reverse string)))

; This will reverse a reversed string
(defn de-mangle
  [mangled-string]
  (str/reverse (str/replace mangled-string "-" "")))

(defn de-mangle2
  [mangled-string]
  (str/reverse (str/replace mangled-string "*" "")))

; In this function we want to determine if a word is a
; palindrome - we use the ? to give it a boolean value
; We use "let" to create some temporary variables only inside this scope
(defn palindrome? [word]
  (let [lower-case (str/lower-case word)
        reversed (str/reverse lower-case)]
    ; This is stating: if the reversed lower-case version of the word is equal
    ; to the plain lower-cased version, return true/else false
    (= reversed lower-case)))

(= [1 2 3] [1 2 3])

; True
(= ["hello" 2 3] [(str "hell" "o") 2 3])

; False
(= [2 3 4] [1 2 3])

; False
(not= ["hello" 2 3] [(str "hell" "o") 2 3])

; COLLECTIONS:
; ** remember you must run the top name-sake of the file
; before you can run any lines in the REPL (cmd + shift + p)

;LISTS:
(list "Dog" 1 3.4 true) ; "Dog" 1 3.4 2

; First index of a list
(first (list "cat" 3 4 5.2))                                ; "cat"

; All indices after the fist of a list
(rest (list "cat" 3 4 5.2))                                ; (3 4 5.2)

; Index of a list
(nth (list "cat" 3 4 5.2) 2)                                ; 4
(nth (list "cat" 3 4 5.2) 0)                                ; "cat"

;Add to a list
(list* 1 2 3 4 [5 6 7])                                     ; (1 2 3 4 5 6 7)

; Add a single value to left side of list
(cons 4 (list 1 2 3))

; SETS:

(set '(1 1 1 1 2))                                          ; {1 2} - sets return unique values only

; For me, this is returning th value if exists, else nil
(get (set '(1 2 2 5 1 6)) 0)                        ; nil
(get (set '(2 3)) 2)                                ; 2

; this joins a value to the set
(conj (set '(3 4 5)) 1)                                     ; {1 4 3 5}

; Check if set contains specific value
(contains? (set '(1 3 4 2)) 3)                              ; true
(contains? (set '(1 3 4 2)) 6)                              ; false

; Remove (dis-join) a value from a set
(disj (set '(1 3 2 4)) 3)                                   ; {1 4 2}


; VECTORS:

; simple vector (array)
(vector 1 "dog")                                            ; [1 "dog"]

; Get specific index from a vector
; **Note vectors are 0-indexed
(get (vector 1 2 3 4) 2)                                    ; 3

; Append an element to a vector
(conj (vector 1 2 3) 4)                                     ; [1 2 3 4]

; To remove an element from a vector
; will pop off the top of the stack
(pop (vector 2 4 6 8))                                      ; [2 4 6]

; To return value from one index point to another
(subvec (vector 1 2 3 4 5) 1 3)                             ; [2 3]
(subvec (vector 1 2 3 4 5) 1 2)                             ; [2]

; MAPS:
; collections of key-value pairs

(hash-map "Name" "John" "Age" 42 "key" "value")             ; {"Age" 42, "key "value", "Name" "John"}

; Sorted by key
(sorted-map 1 "John" 3 42 2 "value")             ; {1 "John", 2 "value", 3 42}

; Get value based on mapped key
(get (hash-map "Name" "John" "Age" 42) "Name")              ; "John"

; Get a key-value pair for key
(find (hash-map "Name" "John" "Age" 42) "Age")              ; ["Age" 42]

; Check if map contains a key
(contains? (hash-map "Name" "John" "Age" 38 "Food" "Popsicles") "Age") ; true

; Return list of keys
(keys (hash-map "Name" "John" "Age" 38 "Food" "Popsicles"))   ; ("Age" "Food" "Name")

; Return list of values
(vals (hash-map "Name" "John" "Age" 38 "Food" "Popsicles"))   ; (38 "Popsicles" "John")

; Merge hash-maps
(merge-with + (hash-map "Name" "John") (hash-map "Age" 38))   ; {"Age" 38, "Name" "John"}

; Atoms:

; This Function demonstrates how we can change values of a variable using atoms
; ** Note the order in which lines are printed based on state
(defn atom-ex
  [x]

  (def atomEx (atom x))

  (add-watch atomEx :watcher
             (fn [key atom old-state new-state]
               ( println "atomEx changed from "
                      old-state " to " new-state)))

  (println "1st x" @atomEx)
  (reset! atomEx 10)
  (println "2nd x" @atomEx)
  (swap! atomEx inc)
  (println "We incremented x by 1 to =" @atomEx)
  )

; Another way to change values is through agents
; Agents allow us to change values using functions
(defn agent-ex
  []

  ;This as is will yield "Tickets 0" because it doesn't wait for the value to update before printing
  (def tickets-sold (agent 0))
  (send tickets-sold + 15)
  ;This will also not allowing for the updated value to be recorded
  (println)
  (println "Tickets " @tickets-sold)

  ;We call the await-for function and then the incrementation will be recorded
  (send tickets-sold + 10)
  (await-for 100 tickets-sold)
  (println "New Total Tickets " @tickets-sold)

  ;This closes the "program" so that it's not still waiting for 100
  (shutdown-agents)

  )

; Math functions
(defn math-stuff
  []

  (println (+ 1 2 3))
  (println (- 5 2 1))
  (println (* 2 5))
  (println (/ 10 5))
  ; mod stands for mod (%)
  (println (mod 2 0))

  (println (inc 5))                                         ;; increment
  (println (dec 5))                                         ;; decrement

  (println (Math/abs -10))                                  ;;Absolute value
  (println (Math/cbrt 8))                                  ;;Cube Root
  (println (Math/sqrt 4))                                  ;;Square Root
  (println (Math/ceil 4.5))                                  ;;Round up
  (println (Math/floor 4.5))                                  ;;Round down
  (println (Math/exp 1))                                  ;;e to the power of 1
  (println (Math/hypot 2 2))                                  ;;sqrt(x^2 + y^2)
  (println (Math/log 2.71828))                                  ;;Natural logarithm
  (println (Math/log10 100))                                  ;;Base 10 log
  (println (Math/max 1 5))                                  ;;
  (println (Math/min 1 5))                                  ;;
  (println (Math/pow 2 2))                                  ;;Power

  ; This will provide a random int up to but not include 20
  (println (rand-int 20))

  (println (reduce + [1 2 3]))

  (println Math/PI)

  )


; Functions

(defn say-hello
  "Receives a name with one parameter and responds"
  [name]

  (println "Hello again" name)

  )


(defn get-sum
  [x y]
  ; By default whatever the output of the last operation from your function is what the return will be
  (+ x y))

(defn get-sum-more
  ([x y z]
   (+ x y z))

  ([x y]
   (+ x y)))

(defn hello-you
  [name]

  (str "Hello " name))

; this is defining a list of names that we can pass as parameters that we are then feeding through by way
;of the map method into our hello-you function
(defn hello-all
  [& names]
  (map hello-you names))

; simple comparison function
(defn can-vote
  [age]
  (if (>= age 18)
    ( println "You can vote!")
    (println "You cannot vote...")
    ))

(defn can-do-more
  [age]
  (if (>= age 18)
    (do (println "You can drive")
        (println "You can vote"))
    (println "You are not 18 or over...")))

(defn when-ex
  [tof]
  (when tof
    (println "1st thing")
    (println "2nd thing")))

(defn what-grade
  [n]
  (cond
    (< n 6) (println "Preschool")
    (= n 6) (println "Kindergarten")
    (and (> n 6) (<= n 18)) (format "You are in grade %d"
                                    (- n 6))
    :else "Go to College"))

; Looping

(defn one-to-x
  [x]
  ;We are using an atom to increment a value because atoms allow us to change a value
  (def i (atom 1))
  (while (<= @i x)
    (do
      (println @i)
      ; to increment you use "swap! i inc"
      (swap! i inc))))

;This will execute a statement a set number of times
; As i has no value it's value is zero based so if 4 is passed to x it increments as follows: 0 1 2 3
(defn dbl-to-x
  [x]
  (dotimes [i x]
    (println (* i 2))))


(defn fizz-buzz
     [start finish]
     (map (fn [n]
    (cond
      (zero? (mod n 15)) "Fizz-Buzz"
      (zero? (mod n 5)) "Buzz"
      (zero? (mod n 3)) "Fizz"
      :else n))
    (range start (+ finish 1))))

;Loop: loop is going to go through values using a statement called recur to change the value
; until a condition is no longer true
(defn triple-to-x
  [x y]
;  starting value of i
  (loop [i x]
    ; while i is less than i,
    (when (< i y)
      ; we record i * 3
      (println (* i 3))
      ;increment i + 1
      (recur (+ i 1)))))

; do sequence
(defn print-list
  ;'&' means list
  [& nums]
  ; x is acting as the temp holding cell for each value of nums
  (doseq [x nums]
    ;so this will iterate through the list and print out the value at each index...I am going to
    ; play with this and effect the value of x
    (println (* x 7))))

; Using File io to read/write to files
(use 'clojure.java.io)

(defn write-to-file
  [file text]
  (with-open [wrtr (writer file)]
    (.write wrtr text)))

(defn read-from-file
  [file]
  (try
    ;slurp is the command used to read from a file
    (println (slurp file))

  (catch Exception e (println "Error: " (.getMessage
                                          e)))))

(defn append-to-file
  [file text]
  (with-open [wrtr (writer file :append true)]
    (.write wrtr text)))

(defn read-line-from-file
  [file]
  (with-open [rdr (reader file)]
    (doseq [line (line-seq rdr)]
      (println line))))





(defn -main
  [& args]

  ; STRiNGS
  ;(def str1 "tHis is my string 1")
  ;
  ;(println (str/blank? str1))
  ;(println (str/includes? str1 "my"))
  ;(println (str/index-of str1 "my"))
  ;(println (str/split str1 #"\d"))
  ;(println (str/split str1 #" "))
  ;(println (str/join " " ["The" "Big" "Lebowski"]))
  ;(println (str/replace "I am 42" #"42" "665"))
  ;(println (str/upper-case str1))
  ;
  ;(atom-ex 5)

  (write-to-file "test.txt" "This is the first sentence in this file\n")
  ;(read-from-file "test.txt")
  (append-to-file "test.txt" "This is the second sentence appended\n")
  (read-line-from-file "text.txt")

  )
