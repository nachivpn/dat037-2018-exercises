{-# OPTIONS -Wall #-}

--------------------------------------------------------------------------------

type Stack = [Char]
type Counter = Integer

-- Removes nested comments of the form (* ... *) from a string.
-- Given N characters, the function takes O(N) time.
removeComments :: Monad m => String -> m String
removeComments s = loop [] 0 s
  where
  loop :: Monad m => Stack -> Counter -> Stack -> m String
  loop a c xs = case xs of
    ('(':'*':cs) -> loop a (c+1) cs
    ('*':')':cs) -> loop a (c-1) cs
    (c1:cs) | c==0      -> loop (c1:a) c cs
            | otherwise -> loop     a  c cs
    [] | c==0 -> return $ reverse a
       | otherwise -> fail "removeComments: unterminated comment"

--------------------------------------------------------------------------------

testsOK :: Bool
testsOK =
     removeComments "c(*a(*b*)*)d" == Just "cd"
  && removeComments "(* a *) d (* b *)" == Just " d "
  && removeComments "(* a (* b *)" == Nothing

main :: IO ()
main = print testsOK

--------------------------------------------------------------------------------