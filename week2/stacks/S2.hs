{-# OPTIONS -Wall #-}

--------------------------------------------------------------------------------

import Data.Char ( isDigit )

--------------------------------------------------------------------------------

type Input = [Token]
type Stack = [Integer]

-- All possible input
data Token = Add | Sub | Mul | Neg | Int Integer
  deriving Show

--------------------------------------------------------------------------------

-- Translates a postfix expression into Input.
parsePostfix :: [String] -> [Token]
parsePostfix [] = []
parsePostfix ("+":ss) = Add : parsePostfix ss
parsePostfix ("−":ss) = Sub : parsePostfix ss
parsePostfix ("*":ss) = Mul : parsePostfix ss
parsePostfix ("-":ss) = Neg : parsePostfix ss
parsePostfix (s:ss) | all isDigit s = Int (read s) : parsePostfix ss
parsePostfix ss = error $ "parsePostfix: parse error: " ++ show (head ss)

-- Evaluates a postfix expression.
-- Given N elements in the input stream, the function takes O(N) time.
evaluate :: Input -> Integer
evaluate = loop []
  where
  loop :: Stack -> Input -> Integer

  -- push integers on the stack
  loop s (Int n:is) = loop (n:s) is

  -- stop when there is no input
  loop [] [] = error "evaluate: empty stack and input"
  loop [n] [] = n
  loop (_:_) [] = error "evaluate: more than one result"

  -- execute operators using the stack
  loop (n2:n1:s) (Add:is) = loop ((n1 + n2):s)  is
  loop _         (Add:_) = error "evaluate: invalid stack"
  loop (n2:n1:s) (Sub:is) = loop ((n1 - n2):s)  is
  loop _         (Sub:_) = error "evaluate: invalid stack"
  loop (n2:n1:s) (Mul:is) = loop ((n1 * n2):s)  is
  loop _         (Mul:_) = error "evaluate: invalid stack"
  loop (n:s)     (Neg:is) = loop ((negate n):s) is
  loop _         (Neg:_) = error "evaluate: invalid stack"

--------------------------------------------------------------------------------

-- given an expression: parse, evaluate, and then show the resulting integer
exec :: String -> String
exec = show . evaluate . parsePostfix . words

--------------------------------------------------------------------------------

-- reads from standard input
main :: IO ()
main = interact exec

-- runs tests
testsOK :: Bool
testsOK = all (\(i,o)->exec i == o)
 [("3 7 +", "10"),
  ("3 7 −", "-4"),
  ("3 4 *", "12"),
  ("4 -", "-4"),
  ("40", "40"),
  ("5 1 2 + 4 * + 3 −", "14")]

--------------------------------------------------------------------------------