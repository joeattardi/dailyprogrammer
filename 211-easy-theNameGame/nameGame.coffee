vowels = 'AEIOU'

nameGame = (name) ->
  theName = name.slice 0, -1
  console.log "#{theName} #{theName} bo #{transform theName, 'B', 'bo'}"
  console.log "Bonana fanna fo #{transform theName, 'F', 'fo'}"
  console.log "Fee fy mo #{transform theName, 'M', 'mo'}"
  console.log name

transform = (name, letter, prefix) ->
  if vowels.indexOf(name[0]) >= 0
    "#{prefix} #{letter}#{name[0].toLowerCase()}#{name.substring 1}"
  else if name[0] is letter
    "#{prefix}-#{name.substring 1}"
  else
    "#{prefix} #{letter}#{name.substring 1}"

nameGame 'Lincoln!'
nameGame 'Nick!'
nameGame 'Arnold!'
nameGame 'Billy!'