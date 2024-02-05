These Data Structures represent the class objects that the Assembler works with.
These class objects have the following relationship/ "flow":

```
Source Program Line
 - Memory Location (Binary Number)
 - Instruction
   - OpCode
   - ArrayList<Field>
     - value (int)
     - size (int)
 - Comments (String) 
```

### Source Program Line

A Source Program Line is the raw text provided from the Source Program file that is fed into the assembler.
It contains:

- Memory Location
- Instruction
- Comments

### Instruction

An Instruction is the core of the source program that will be assembled down into machine code.
Each Instruction contains:

- Op Code
- Ordered list of Fields

### Op Code (Operation Code)

An Op Code (Operation Code) are the first 6 bits of an Instruction, and corresponds with a specific command, or
operation, to be executed by the machine.
In this implementation, the OpCode class extends the Field class for convince.

### Field

A Field is a binary number of a specific length. Each Field has:

- A value
- A size

### Binary Number

A Binary Number is a convince class to represent binary numbers.
Under the hood it's an array of booleans, but has utility functions to/from base10, base8 (octal) or base2 (binary).