# Tortoise
A turtle manager for the Skript plugin
Turtles are invisible entities that have a location, an orientation and optionally a function which they can execute. Turtles can have parents and children. Children follow always their parents. If a child turtle is 3 meters left of it's parent it will stay 3 meters left of it's parent even if the parent moves or rotates.

Creating a new turtle:
```
new turtle at %location%
clone [of] %turtle%
```

Removing a turtle:
```
remove %turtles%
```

Turtle ID:
```
id of %turtle%
%turtle['s] id
turtle %string%
```

Moving a turtle:
```
move %turtles% to %location%
move %turtles% by %number%, %number%, %number%
move %turtles% by %vectors%
```

Orientation control:
```
rotate %turtles% by %number%, %number%, %number%
rotate %turtles% by %vectors%
align (forward|backward|upward|downward|right[ward]|left[ward])(-| )axis of %turtles% with %vector%
```

Turtle family expressions:
```
all turtles
(children|descendants) of %turtle%
%turtle%['s] (children|descendants)
%turtle% has parent
%turtle% is (parent|child|descendant) of %turtle%
parent of %turtle%
%turtle%['s] parent
```

Turtle functions:
```
set function of %turtles% to %function%
(run|execute) function of %turtles%
(run|execute) %turtles%['s] function
release %turtles% [with (children|descendants)]
stop %turtles%
heartbeat of %turtle%
```

Additional expressions:
```
turtle name of %turtle%
%turtle%['s] turtle name
location (of|at) %turtle%
%turtle%['s] location
location %vectors% from %turtle%
location %number%,[ ]%number%, [ ]%number% from %turtle%
(forward|backward|upward|downward|right[ward]|left[ward])(-| )axis of %turtle%
%turtle% follows rotation of parent
```
