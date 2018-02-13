Java 8 for building and running is strongly recommended.

Type
    mvn clean install
in the console and also an executable .jar is
created (done in install phase).

Find the executable .jar named 'Tilgungsrechner.jar' on the top project level
for execution.

unsatisfying things and worth discussing:

- All types are strings in CalculatorInput.
  So, in times when it isn't uncommon to use json for input-parameters it is acceptable?
  Where the conversion should take place?

- Using BigDecimal or double for calculation.
  BigDecimal gives us full control of precision and rounding (try 1/3*3 with double and You will get 1.0) and
  also is recommended for financial calculations.

  Primitive double is faster and more readable. And also satisfy the needs here.


