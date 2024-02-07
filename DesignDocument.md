# Assembler Design Document

The Assembler project is subdivided into 3 main modules:

- Source Program Reader
- Field Processors
- Data Structures

Each module is described in detail below.
But the overall the Source Program Reader is responsible for opening the input Source Program File, considering it line
by line, and dispatching each instruction to a matching Field Processor. There is a field processor for each type of
Operation Code (OpCode). Field Processors take the field numbers from the Source Program File, convert them to the
correct, in assembled order, Field representation.

For example, consider the Source Program line:
`17	LDR	1,2,10,1 ;R1 GETS 18`

The Source Program Reader will split this line into a few tokens:

- Memory Location(optional): `17`
- OpCode (required): `LDR`
- Fields (OpCode Dependent): `1,2,10,1`
- Comments (Optional): `;R1 GETS 18`

Depending on the OpCode, a Field Processor will be selected. In this case, an `RXAIFieldProcessor` instance.
The Field Processor will then be given the Fields `1,2,10,1`, and the Field Processor will return the ordered collection
of Fields. A Field is a representation of a Binary Number. It has a value, and it has a bit-length.

```
[ 
  Field{value=1, size=2},   // Register bits
  Field{value=2, size=2},   // IX bits
  Field{value=1, size=1},   // Indirection Bit
  Field{value=10, size=5}   // Address Bits
]
```

NOTE: Notice that the fields provided into the Field Processor had the indirection bit `1` as the last field, but the
Field Processor moved it around in the returned list of Fields.

Once the Source Program line has been split, it's fields have been processed, then it's simply a matter of converting
everything into the expected binary form and writing to an output file.

```
Input:  17	LDR 1,2,10,1                             ;R1 GETS 18
Output: 000021	002652             17	LDR	1,2,10,1 ;R1 GETS 18
```

In the above example the various elements are lined up by adding whitespace.
Notice the input `17` matches with the output `000021`, and the input `LDR 1,2,10,1` matches with the output `002652`

## Source Program Reader

The Source Program Reader is responsible for reading the Source Program File, tokenizing each line and dispatching the
tokens to the appropriate Field Processors.
Once all the data has been processed, the Source Program Reader will also convert all the data into it's appropriate
Hex(base 8) representation.
These Hex encoded instructions will then be saved to a file.

For a little more detail, each line of text from the source program file will be converted into a `SourceProgramLine`
object (see the Data Structures module below for more detail). These `SourceProgramLine` objects contain all the same
data as the `String` representation found in the Source Program File, but as a Java Object allowing for functionality
like `.assemble()` to be added.

#### Source Program Options

The Source Program Reader has a few options available that determine what the assembled output should include, or how it
should look.
For example, there is an option called `repeatSourceProgramLine` that determines weather or not the input in it's
entirety should be re-printed into the output.
This is useful for debugging the assembler.

Example:

```
Input: 17	LDR 1,2,10,1    ;R1 GETS 18

Output(RepeatSourceProgramLine): 000021	002652  17	LDR	1,2,10,1 ;R1 GETS 18
Output(!RepeatSourceProgramLine):000021	002652
```

#### Source Program Input File

The Source Program Input File MUST be formatted properly. Each token must be seperated by a whitespace character ` ` (
space) or `\t` tab.

***Important***: Even if the token is optional, the determiner is not optional.

Consider the first two source program file lines from the project example:

```
	LOC	6			    ;BEGIN AT LOCATION 6
6	Data	10			;PUT 10 AT LOCATION 6
```

Notice that the first line (including `LOC`) starts with a `\t` tab character, while the next line starts with a memory
location. The memory location is optional, but the white space delimiter is not.

#### Memory Location

The memory location column is optional. The Assembler will track the memory location, starting at location 0, increasing
it by 1 for each instruction line. Unless there is a `LOC` instruction, in which case the assembler will use the fields
of that `LOC` instruction as the memory location for the next field.

## Field Processors

These Field Processors are dedicated function wrappers that take a Source Program field data, and (depending on the op
code) turn them into an ordered collection of Field objects.

This is useful, because sometimes the source program provides the field values in a different order than they are
supposed to be in the assembled machine code.
For example consider this Source Program line:

`ldr 2,3,4,1`

The 4 field values `2,2,2,1` correspond with 4 different fields in the order provided from the Source Program Line:

General Purpose Register (RR) = `Field{value=2, size=2}`

Index Register (XI) = `Field{value=3, size=2}`

Address (Adr) = `Field{value=4, size=5}`

Indirection Bit (I) = `Field{value=1, size=1}`

However, these fields get assembled down into a binary number that looks like this:

`RRXXIAAAAA`

Notice that the Indirection Bit is provided at the end of the source program values, but once assembled it's sandwiched
between the XI and Address sections.
The Field Processor classes are responsible for extracting out the fields from the Source Program line, and placing them
in the correct assembler order.

#### Types of Field Processors:

There are many types of Field Processors to reflect the many types of Operation Codes, and their various expected
fields.
A complete mapping of OpCode to Field Processor can be found in the `FieldProcessorFactory` class. But in summary:
There are (at time of writing) 10 different types of Field Processors:

- Bitwise Operations
- Data
- HLT
- IO
- LOC
- Register to Register
- Register to Register (NOT)
- RXAI
- Trap

#### RXAI Field Processor

Perhaps the most complicated Field Processor is the RXAI field processor.
There are many different OpCodes that have fields that are in the RXAI format, that is:

- general Register
- indeX register
- Address
- Indirection bit

These instructions will assemble down into the following binary representation:

```
 * [OPOPOP RR XX I AAAAA]
 *  0    5 67 89 1 1   1
 *               0 1   5
```

- OpCode use bits 0-5
- General Register use bits 6-7
- Index Register uses bits 8-9
- Indirection Bit uses bit 10
- Address uses bits 11-15

Various OpCodes use some subset of these fields. For example: the `LDR` OpCode uses all R, X, A (and optionally I)
fields.
However, the `LDX` OpCode only uses X A (and optionally I) but NOT R.
The `FieldProcessorFactory` knows which OpCodes require which fields, and configures the `RXAIFieldProcessor` to expect
only those fields.
Any fields that the OpCode is not expecting will be filled with 0s in that fields bit location.
For example, the `LDX` OpCode will always have `0`s in the bits that correspond with the General Register (bits 6 and
7).

## Data Structures

There are several useful Data Structures used to represent concepts and data in this assembler.

#### Binary Number

Perhaps the most basic data structure is the Binary Number. This is a utility class that allows a value to be
represented as a binary number, a hexadecimal number, a base 10 number etc.
This class is more of a utility class for converting to/ from binary as opposed to a meaningfully class for the
Assembler
architecture.

#### Field

A Field is a value and a bit length. This is the most fundamental building block upon which most all other data objects
are built.
When printed as a binary number, a field will always be printed to it's bit length, with 0s appended to the left as
needed.

#### OpCode

An OpCode (Operation Code) is-a Field. OpCods always have a size of 6 bits, and a name (string).
Each name is associated with a well-known fixed value. See the `OpCode.opCodeToValue(...)` for details.

#### Instruction

An instruction is made up of a single OpCode, and an ordered set of Fields.
The total "length" of an Instruction, IE the size of the OpCode + the sum of the Fields lengths, should always sum to 16
bits.

An instruction offers the `assemble()` function that converts itself into a fully assembled octal instruction.

#### Source Program Line

A SourceProgramLine is a full line of input given from the Source Program.
It is made up of 3 main parts:

- Memory Location (Binary Number)
- Instruction
- Comments (Strings)
