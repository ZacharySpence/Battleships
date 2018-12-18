//Map creation function
//game board
var Board = Array.ofDim[String](100,100)
def createGameBoard(width:Int,length:Int)={
	var nlength = (length*3) +2
	Board = Array.ofDim[String](width+1,nlength+1)
	var A = 0 
	for (A <- 0 to width){
		var B = 0
		var C = 0

		for (B <- 0 to nlength){
			if (Board(A)(B) == null){
				if ((B == (nlength/3) +1) || (B== nlength - (nlength/3))){
						Board (C)(B) = " | "		
					}
				else{
					if (A== 0 && B == 0){
						Board(A)(B) = " ~  "
						if (B <= length){
							print(Board(A)(B))
						}
						
					}
					else if (A==0 && B ==10){
							Board(A)(B) = (s" $B")
							print(Board(A)(B))
					}
					else if (A == 0 && B > 0){
						Board(A)(B) = (s" $C ")
						if (B <= length){
							print(Board(A)(B))
						}
					}
					else if (A > 0 && B == 0){
						if (A < 10){
							Board(A)(B) = (s" $A  ")
						}
						else{
							Board(A)(B) = (s" $A ")
						}
						
						if (B <= length){
							print(Board(A)(B))
						}
					}
					else{
						Board(A)(B) = " ~ "
						if (B <= length){
							print(Board(A)(B))
						}
					}
				}
			}
			else{
				if (B <= length){
					print(Board(A)(B))
					}
			}

			if (C >= length){
				C = 0
			}
			else{
				C+=1
			}
			

		}
		print("\n")
	}
}
createGameBoard(10,10)

//Print Game Board
def printGameBoard(size:Int)={
	for (A <-0 to 10){
		for (B <- 0 to size){
			if ((Board(A)(B) == null || Board(A)(B) == "|")|| Board(A)(B) == " | "){
				Board(A)(B) = " | "
			}
			print(Board(A)(B))
		}
		print("\n")
	}
}


//Collision detection
def collisionDetection(x:Int,y:Int,hv:String,ship:String,place:Boolean,person:String): Int={

	var k = 0

	var space = (ship match{
		case "carrier" => 5
		case "battleship" => 4
		case "cruiser" | "submarine" => 3
		case "destroyer" => 2
		})

	var name = (ship match{
		case "carrier" => "C"
		case "battleship" => "B"
		case "cruiser"  => "c"
		case "submarine" => "S"
		case "destroyer" => "D"
		})
	if (person == "player"){
		//return shipPlacement(x,y,hv,space,name,place)
	//}
		if (hv == "right"){
			k = x
			while (k < x+(space.toInt)){
				if (place){
					Board(y)(k) = (s" $name ")
				}
				else{
					if (Board(y)(k) != " ~ "){
						return 1
					}
				}
				k+=1
			}
		}
		else if (hv == "left"){
			k = x
			while (k > x-(space.toInt)){
				if (place){
					Board(y)(k) = (s" $name ")
				}
				else{
					if (Board(y)(k) != " ~ "){
						return 1
					}
				}
				k-=1
			}
		}
		else if (hv == "down"){
			k = y
			while(k < y+(space.toInt)){
				if (place){
					Board(k)(x) = (s" $name ")
				}
				else{
					if (Board(k)(x) != " ~ ") {
						return 1
					}
				}
				k+=1
			}
		}
		else if (hv == "up"){
			k = y
			while(k > y-(space.toInt)){
				if (place){
					Board(k)(x) = (s" $name ")
				}
				else{
					if (Board(k)(x) != " ~ "){
						return 1
					}
				}
				k-=1
			}
		}
	}
	else{
		//computer -> 
		var posoptions = Array("left","right","up","down")
		//Check if start position != " ~ "
		if (Board(y)(x) != " ~ "){
			return 1
		}
		else{
			//Left Option
			if ((x-space)< 21){
				//remove Left option
				posoptions(0) = null
			}
			else{
				for(k <- x-space to x){
					if (Board(y)(k) != " ~ "){
						//remove Left option
						posoptions(0) = null
					}

				}
			}
			//Right Option
			if ((x+space) > 31){
				//remove Right option
				posoptions(1) = null
			}
			else{
				for(k <- x to x+space){
					if(Board(y)(k) != " ~ "){
						//remove Right option
						posoptions(1) = null
					}
				}
			}
			//Down Option
			if ((y+space) > 10){
				//remove Down option
				posoptions(3) = null
			}
			else{
				for(k <- y to y+space){
					if(Board(k)(x) != " ~ "){
						//remove Down option
						posoptions(3) = null
					}
				}
			}
			//Up option
			if ((y-space) < 1){
				//remove Up option
				posoptions(2) = null
			}
			else{
				for(k <- y-space to y){
					if(Board(k)(x) != " ~ "){
						//remove Up option
						posoptions(2) = null
					}
				}
			}
			if (posoptions.length == 0){
				//get coords again
				return 1
			}
			else{
				//choose from options
				var pos = posoptions(randInt.nextInt(posoptions.length))
				while (pos == null){
					pos = posoptions(randInt.nextInt(posoptions.length))
				}
				println("Position: "+ pos)
				//return shipPlacement(x,y,pos,space,name,place)
				//place coords into an array
				eSP += space
				print("EsP: " + eSP)
				placeEShips(x,y,eSP,space,pos,name)
			}
		}
	}
	return 0
}

//placing enewmy ships into an array
def placeEShips(x:Int,y:Int,eSP:Int,space:Int,positions:String,name:String)={
	var x1 = x
	var y1 = y
	

	for (k <- eSP to eSP+space-1){
		enemyShipsPositions(k) = (s"($x1"+","+ s"$y1)")
		println(enemyShipsPositions(k)+ "HERE")
		positions match{
		case "left"  => Board(y1)(x1) = (s" $name ")
			x1-=1
		case "right" => Board(y1)(x1) = (s" $name ")
			x1+=1
		case "down"=> Board(y1)(x1) = (s" $name ")
			y1+=1	
		case "up"  => Board(y1)(x1) = (s" $name ")
			y1-=1
		}
	}
}
//place Ships function
def placeShips(x:Int,y:Int,hV:String,ship:String, person:String)={
	if (collisionDetection(x,y,hV,ship,false,person) > 0){
		if (person == "player"){
			println("Thats an illegal position")
			print("\n")
		}
	}
	else{
		collisionDetection(x,y,hV,ship,true,person)
		psEnd = 1
	}
}
//building ships
//getting coordinates
def coordGet(number:String):Int={
	number match{
			case "1" | ",1" | "1," => 1
			case "2" | ",2" | "2," => 2
			case "3" | ",3" | "3," => 3
			case "4" | ",4" | "4," => 4
			case "5" | ",5" | "5," => 5
			case "6" | ",6" | "6," => 6
			case "7" | ",7" | "7," => 7
			case "8" | ",8" | "8," => 8
			case "9" | ",9" | "9," => 9
			case "10"| ",10"| "10,"=> 10
		} 
}

def makeCoordinates(person:String)={
	if (person == "player"){
			println("Player 1, Choose your " + ships(end) + " => starting (x,y) Coordinate")
		var coordinates = readLine()
		xCoord = coordGet(coordinates.substring(0,2))
		yCoord = coordGet(coordinates.substring(2))
			println("Is it Up, Down, Left or Right?")
			horV = readLine().toLowerCase
		
	}
	else{
		//computer
			xCoord = randInt.nextInt(10)+23
			yCoord = randInt.nextInt(10)+1		
	}	 
}

//
println("\n"+"\n")
	var end = 0
	var psEnd = 0
	var xCoord = 0
	var yCoord = 0
	var horV = ""
	var enemyShipsPositions = new Array[String](30)
	var eSP = 0
	var randInt = scala.util.Random
	var ships = Array("carrier", "battleship","cruiser","submarine","destroyer", "done")

def create(person:String)={
	end = 0
	while (ships(end) != "done"){
			while(psEnd == 0){
				makeCoordinates(person)
				placeShips(xCoord, yCoord, horV, ships(end),person)
			}
		if (person == "player"){
			println("You have placed your " + ships(end) + " At ("+xCoord+","+yCoord+")")
			printGameBoard(10)
		}
		end += 1
		psEnd = 0 
	}
/*if (person == "computer"){
	//delete ship positions
	for (k<-1 to 10){
		for(j <- 11 to 21){
			if (Board(k)(j) == null || Board(k)(j) == " | "){
				Board(k)(j) = " | "
			}
			else{
				if (Board(k)(j) != " ~ "){
					Board(k)(j) = " ~ "
				}
			}
			
		}
	}
}*/
}

create("player")
create("computer")
printGameBoard(21)
//for (i <- 0 to enemyShipsPositions.length-1){
	//println(enemyShipsPositions(i))
//}



//Shooting
def shot(person:String): String={
	if (person == "player"){
		print("Hello")
		println("Please Choose which coordinates to shoot Captain")
		var coordinates = readLine
		println(coordinates)
		xCoord = coordGet(coordinates.substring(0,2))
		println(xCoord)
		yCoord = coordGet(coordinates.substring(2))
		println(yCoord)
		println("What you hit:"+ Board(yCoord)(xCoord+22))
		if ((Board(yCoord)((xCoord + 22)) == " ~ " ||Board(yCoord)((xCoord + 22)) == " X ") || Board(yCoord)((xCoord + 22)) == " o ") {
			if(Board(yCoord)((xCoord + 22)) == " ~ "){
				Board(yCoord)((xCoord + 22)) = " o "
				Board(yCoord)((xCoord + 11)) = " o "

			}
			println("You missed!")
			return "miss"
		
		}
		else{
			Board(yCoord)((xCoord + 22)) = " X "
			Board(yCoord)((xCoord + 11)) = " X "
			println("hit")
			return "hit"	
		}	
	}

	else{
		xCoord = randInt.nextInt(11)+1
		yCoord = randInt.nextInt(10)+1

		//put player ships in array sometime
		for (y <- 1 to 10){
			for (x <- 1 to 10){
				if (Board(yCoord)(xCoord) != " ~ "){
					println("You've been hit")
					Board(yCoord)(xCoord) == " X "
					return "hit"
				}
			}
		}
		println("Computer misses you")
		Board(yCoord)(xCoord) = " o "
		return "miss"
	}
	return "miss"
}
//Gameplay
var turn = "player"
var phits = 0
var ehits = 0
while (phits < 17 && ehits < 17){
	turn match{
		case "player" =>
			if  (shot("player") == "hit"){
				phits +=1
				println("your hits:"+ phits)

			}
			else{
				turn = "computer"
			}
			
		case "computer" =>
			if (shot("computer") == "hit"){
				ehits += 1

			}
			else{
				turn = "player"
			}
	}
	printGameBoard(21)
}
if (phits == 17){
	println("YOU WON")
}
else{
	println("YOU LOST")
}
