class Point
  constructor: (@x, @y) ->

  toString: ->
    "#{@x},#{@y}"

class OgreMaze
  constructor: (mazeStr) ->
    @buildMaze(mazeStr)
    @ogre = @findOgre()

  buildMaze: (mazeStr) ->
    @maze = []
    mazeStr.split('\n').forEach (line) =>
      @maze.push line.split ''

  findOgre: (character) ->
    i = 0
    while i < @maze.length
      index = @maze[i].indexOf '@'
      if index >= 0
        return new Point(index, i)
      i++

  foundLoot: (point) ->
    @maze[point.y][point.x] is '$' or
    @maze[point.y + 1][point.x] is '$' or
    @maze[point.y][point.x + 1] is '$' or
    @maze[point.y + 1][point.x + 1] is '$'

  pointPassable: (point) ->
    valid = '@.$'
    valid.indexOf(@maze[point.y][point.x]) >= 0 and
    valid.indexOf(@maze[point.y + 1][point.x]) >= 0 and
    valid.indexOf(@maze[point.y][point.x + 1]) >= 0 and
    valid.indexOf(@maze[point.y + 1][point.x + 1]) >= 0


  findReachablePoints: (point) ->
    reachablePoints = []
    if point.x > 0 and @pointPassable(new Point(point.x - 1, point.y))
      reachablePoints.push new Point(point.x - 1, point.y)
    if point.x < 8 and @pointPassable(new Point(point.x + 1, point.y))
      reachablePoints.push new Point(point.x + 1, point.y)
    if point.y > 0 and @pointPassable(new Point(point.x, point.y - 1))
      reachablePoints.push new Point(point.x, point.y - 1)
    if point.y < 8 and @pointPassable(new Point(point.x, point.y + 1))
      reachablePoints.push new Point(point.x, point.y + 1)
    reachablePoints

  buildPath: (previous, lootPoint) ->
    path = [lootPoint]
    previousPoint = previous[lootPoint]
    while previousPoint
      path.unshift previousPoint
      previousPoint = previous[previousPoint]
    path

  printPath: (path) ->
    path.forEach (point) =>
      @maze[point.y][point.x] = '&'
      @maze[point.y][point.x + 1] = '&'
      @maze[point.y + 1][point.x] = '&'
      @maze[point.y + 1][point.x + 1] = '&'
    
    @maze.forEach (row) ->
      console.log row.join ''

  findPath: ->
    previous = []
    visited = []
    toVisit = []

    toVisit.push @ogre
    while toVisit.length
      point = toVisit.pop()
      if @foundLoot point
        path = @buildPath previous, point
        @printPath path
        return
      visited.push point.toString()
      @findReachablePoints(point).forEach (nextPoint) ->
        if visited.indexOf(nextPoint.toString()) < 0
          previous[nextPoint] = point
          toVisit.push nextPoint

    console.log 'No Path'


maze = '''
$.O...O...
...O......
..........
O..O..O...
..........
O..O..O...
..........
......OO..
O..O....@@
........@@
'''
maze = new OgreMaze(maze)
maze.findPath()