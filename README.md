# Tortoise
A turtle manager for the Skript plugin
Turtles are invisible entities that have a location, an orientation and optionally a function which they can execute. Turtles can have parents and children. Children follow always their parents. If a child turtle is 3 meters left of it's parent it will stay 3 meters left of it's parent even if the parent moves or rotates.
![Gif Example 1](https://i.gyazo.com/280c0e891788a6f33e27d3c5bd4660a8.gif)
![Gif Example 2](https://i.gyazo.com/f560f65fe05f97260f26916574bb52b7.gif)
![Gif Example 3](https://i.gyazo.com/bdd4335239b75a6b207b255ff3329639.gif)

Creating a new turtle:
```
new turtle at %location%
clone [of] %turtle%
```

Removing a turtle:
```
remove (0¦|1¦with childern|2¦with descendants) %turtles%
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
stop (0¦|1¦with childern|2¦with descendants) %turtles%
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
