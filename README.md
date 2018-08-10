# anderbot
experimental discord bot

## Code Structure
- The core idea is to move all Discord commands to a properties file, and utilizing Spring proxying to automate most of the object instantiation. This aims to reduce the boilerplate code in each of the actual classes for commands.
- The `command` package has classes for individual commands
- The `event` package has custom events for parsing "sub-commands" and other arguments
- The `listener` package contains custom listeners (used for parsing messages that might not start with our prefix)
- The `message` package has nicely pre-formatted Discord embedded messages 
- The `util` package contains some useful chat and bot utilities
#### Individual pieces
- The main runner is responsible for authenticating the bot with Discord, registering event listeners, etc
- The command handler is a generic class that parses user input into a command and calls the correct command class to receive it
- The listener handler registers individual event listeners
  - Mostly used for commands that don't necessarily have the `>` prefix
- An event dispatcher parses any arguments that come after a root command
  - (eg, `>dump add x y` - the `dump` command parses the initial `dump`, which fires off the handler for the `add` command using a dispatcher)
- The main class for a command is `AbstractCommand`, which contains the wireframe to be fllowed for parsing events 
   

## Roadmap
In no particular order:
- Improve how command statuses are passed
- Centralize all generic message responses
- Somewhere to store magic strings
- Tokens via command line argument instead
- Easy commands: help, dump, roll (+coin flip), 8 ball, fortune telling, Spongebob meme
- Hard commands: bilge dice, othello, stock market, blackjack, other gambling bots, full rpg?
- Stats
- osu! api integration (?)

## Other Small things
- Dumping on for x amount of messages

## Changelog
1.0-SNAPSHOT:
 - Initial version!
 - Added commands "dump", "help", "roll", "play"