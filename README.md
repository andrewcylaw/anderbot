# anderbot
experimental discord bot

## Notes
- Perhaps a generic message handler interface, with implementations registered as Spring beans
- The implementations have an "identifier" (ie the command)
- Register all of the beans to the message event listener with the identifiers
