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

## Custom Commands

G2X RR, IX
General Purpose Register to Index Register.
Take the data in the targeted GPR and copy it into the targeted IX.
If the IX register is 0, then this is effectively a no-op

X2G RR, IX
Index Register to General Purpose Register
Take the data in the targeted IX register and copy it into the targeted GPR.
If the IX register is 0, then the value loaded into the GPR will be 0.