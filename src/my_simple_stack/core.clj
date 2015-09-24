(ns my-simple-stack.core
  "avoid namespace collision with pop and peek
  do not use size fucking size OR empty they are special 'Duplicate method name&signature' errors"
  (:refer-clojure :exclude [pop peek length empty]))

(defprotocol PStack
  (empty-stack [this])
  (length [this])
  (peek [this])
  (push [this element])
  (tail [this])
)

(defrecord Stack [elements]
  PStack
  (push [_ element]
     (->Stack (concat element elements )))
    ; vector style(->Stack (into element elements )))
  (tail [_]
    (->Stack(rest elements)))
  (peek [_] 
    (first elements))
  (length [_]
    (count elements))
  (empty-stack [_]
    (= (count elements) 0))
  )

(defn -main
  [& args]

  (def stack (->Stack nil))
  (println "empty-stack? = " (empty-stack stack))

 ;vector style (def stack (->Stack [1 2 3 4 5 ]))
  (def stack (->Stack (list 1 2 3 4 5 )))
  (println "New Stack() = "  stack)
  (println "Size = "  (length stack))
  (def stack (push stack [6]))
  (println "stack2 = " stack)
  (println "peek stack3 = " (peek stack))
  (def stack (tail stack))
  (println "Size = "  (length stack))
  (println "stack4 = " stack)
  (println "empty-stack? = " (empty-stack stack))
)
