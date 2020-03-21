package stackmazesolver;

/**
 * Author: Rafael Mendoza
 * Description: This class determines the exit path if a path exists. Two (two dimensional arrays) are used to determine the path. The two dimensional array of strings is used
 * to validate if the current position can move to a certain direction. The two dimensional array of integers is used to store values that represent the places that have been visited.
 * The two dimensional array of integers is also used to display the path of the maze.
 */
public class MazeSolver {
    private int[][] visited;
    private String[][] array;
    private int xPos;
    private int yPos;
    private boolean pathExist;
    private LinkedStack stack;

    public MazeSolver(String[][] array, int xPos, int yPos) {
        this.pathExist = false;
        this.array = array;
        this.visited = new int[array.length][array[0].length];
        this.visited[xPos][yPos] = 1;
        this.stack = new LinkedStack();
        this.xPos = xPos;
        this.yPos = yPos;
        stack.push(xPos);
        stack.push(yPos);

    }

    public int[][] getVisited() {
        return this.visited;
    }

    public boolean getPathExist() {
        return this.pathExist;
    }

    public void mazeSolver() {
        int a = 0;
        while (!(this.stack.isEmpty())) {
            if (this.xPos == 0 && this.yPos > 0 && this.yPos < this.array[0].length - 1) {
                a = firstCase();
               if (a == 1) {
                   this.pathExist = true;
                   break;
               }
            }
            else if (this.xPos > 0 && this.xPos < this.array.length - 1 && this.yPos > 0 && this.yPos < this.array[0].length - 1) {
                a = secondCase();
                if (a == 1) {
                    this.pathExist = true;
                    break;
                }
                else if (a == 2){
                    break;
                }
            }
            else if (this.xPos > 0 && this.xPos < this.array.length - 1 && this.yPos == 0) {
                a = thirdCase();
                if (a == 1) {
                    this.pathExist = true;
                    break;
                }
                else if (a == 2){
                    break;
                }
            }
            else if (this.xPos == this.array.length - 1 && this.yPos > 0 && this.yPos < this.array[0].length - 1) {
                a = fourthCase();
                if (a == 1) {
                    this.pathExist = true;
                    break;
                }
                else if (a == 2){
                    break;
                }
            }
            else if (this.xPos > 0 && this.xPos < this.array[0].length && this.yPos == this.array[0].length) {
                a = fifthCase();
                if (a == 1) {
                    this.pathExist = true;
                    break;
                }
                else if (a == 2){
                    break;
                }
            }
            else if (this.xPos == 0 && this.yPos == 0) {
                a = sixthCase();
                if (a == 1) {
                    this.pathExist = true;
                    break;
                }
                else if (a == 2){
                    break;
                }
            }
            else if (this.xPos == this.array.length - 1 && this.yPos == 0) {
                a = seventhCase();
                if (a == 1) {
                    this.pathExist = true;
                    break;
                }
                else if (a == 2){
                    break;
                }
            }
            else if (this.xPos == this.array.length - 1 && this.yPos == this.array[0].length - 1) {
                a = eigthCase();
                if (a == 1) {
                    this.pathExist = true;
                    break;
                }
                else if (a == 2){
                    break;
                }
            }
            else if (this.xPos == 0 && this.yPos == this.array[0].length - 1) {
                a = ninthCase();
                if (a == 1) {
                    this.pathExist = true;
                    break;
                }
                else if (a == 2){
                    break;
                }
            }
        }
    }


    public int firstCase() {
        int value = 0;
            if (this.array[this.xPos][this.yPos + 1].equalsIgnoreCase("E") && this.visited[this.xPos][this.yPos + 1] != 1 && this.visited[this.xPos][this.yPos + 1] != -1) {
            this.yPos = this.yPos + 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
            value = 1;
            }
         else if (this.array[this.xPos + 1][this.yPos].equalsIgnoreCase("E") && this.visited[this.xPos + 1][this.yPos] != 1 && this.visited[this.xPos + 1][this.yPos] != -1) {
            this.xPos = this.xPos + 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
            value = 1;
            }
            else if (this.array[this.xPos][this.yPos - 1].equalsIgnoreCase("E") && this.visited[this.xPos][this.yPos - 1] != 1 && this.visited[this.xPos][this.yPos - 1] != -1) {
            this.yPos = this.yPos - 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
            value = 1;
            }
             else if (this.array[this.xPos][this.yPos + 1].equalsIgnoreCase("1") && this.visited[this.xPos][this.yPos + 1] != 1 && this.visited[this.xPos][this.yPos + 1] != -1) {
                this.yPos = this.yPos + 1;
                this.visited[this.xPos][this.yPos] = 1;
                this.stack.push(this.xPos);
                this.stack.push(this.yPos);
            }
            else if (this.array[this.xPos + 1][this.yPos].equalsIgnoreCase("1") && this.visited[this.xPos + 1][this.yPos] != 1 && this.visited[this.xPos + 1][this.yPos] != -1) {
                this.xPos = this.xPos + 1;
                this.visited[this.xPos][this.yPos] = 1;
                this.stack.push(this.xPos);
                this.stack.push(this.yPos);
            }
            else if (this.array[this.xPos][this.yPos - 1].equalsIgnoreCase("1") && this.visited[this.xPos][this.yPos - 1] != 1 && this.visited[this.xPos][this.yPos - 1] != -1) {
                this.yPos = this.yPos - 1;
                this.visited[this.xPos][this.yPos] = 1;
                this.stack.push(this.xPos);
                this.stack.push(this.yPos);
            }
            else {
                this.yPos = (int) this.stack.top();
                this.stack.pop();
                this.xPos = (int) this.stack.top();
                this.stack.pop();
                this.visited[this.xPos][this.yPos] = -1;
                if (!(this.stack.isEmpty())) {
                    if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos - 1][this.yPos].equalsIgnoreCase("1") &&
                            this.visited[this.xPos - 1][this.yPos] == 0) {
                        this.visited[this.xPos][this.yPos] = 1;
                        this.stack.push(this.xPos);
                        this.stack.push(this.yPos);
                    } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos][this.yPos + 1].equalsIgnoreCase("1") &&
                            this.visited[this.xPos][this.yPos + 1] == 0) {
                        this.visited[this.xPos][this.yPos] = 1;
                        this.stack.push(this.xPos);
                        this.stack.push(this.yPos);
                    } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos + 1][this.yPos].equalsIgnoreCase("1") &&
                            this.visited[this.xPos + 1][this.yPos] == 0) {
                        this.visited[this.xPos][this.yPos] = 1;
                        this.stack.push(this.xPos);
                        this.stack.push(this.yPos);
                    } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos][this.yPos - 1].equalsIgnoreCase("1") &&
                            this.visited[this.xPos][this.yPos - 1] == 0) {
                        this.visited[this.xPos][this.yPos] = 1;
                        this.stack.push(this.xPos);
                        this.stack.push(this.yPos);
                    }
                }
                else {
                    value = 2;
                }
            }
        return value;
    }

    public int secondCase() {
        int value = 0;
         if (this.array[this.xPos - 1][this.yPos].equalsIgnoreCase("E") && this.visited[this.xPos - 1][this.yPos] != 1 && this.visited[this.xPos - 1][this.yPos] != -1) {
            this.xPos = this.xPos - 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
            value = 1;
        }
        else if (this.array[this.xPos][this.yPos + 1].equalsIgnoreCase("E") && this.visited[this.xPos][this.yPos + 1] != 1 && this.visited[this.xPos][this.yPos + 1] != -1) {
            this.yPos = this.yPos + 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
            value = 1;
        }
        else if (this.array[this.xPos + 1][this.yPos].equalsIgnoreCase("E") && this.visited[this.xPos + 1][this.yPos] != 1 && this.visited[this.xPos + 1][this.yPos] != -1) {
            this.xPos = this.xPos + 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
            value = 1;
        }
        else if (this.array[this.xPos][this.yPos - 1].equalsIgnoreCase("E") && this.visited[this.xPos][this.yPos - 1] != 1 && this.visited[this.xPos][this.yPos - 1] != -1) {
            this.yPos = this.yPos - 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
            value = 1;
        }
        else if (this.array[this.xPos - 1][this.yPos].equalsIgnoreCase("1") && this.visited[this.xPos - 1][this.yPos] != 1 && this.visited[this.xPos - 1][this.yPos] != -1) {
            this.xPos = this.xPos - 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
        }
        else if (this.array[this.xPos][this.yPos + 1].equalsIgnoreCase("1") && this.visited[this.xPos][this.yPos + 1] != 1 && this.visited[this.xPos][this.yPos + 1] != -1) {
            this.yPos = this.yPos + 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
        }
        else if (this.array[this.xPos + 1][this.yPos].equalsIgnoreCase("1") && this.visited[this.xPos + 1][this.yPos] != 1 && this.visited[this.xPos + 1][this.yPos] != -1) {
            this.xPos = this.xPos + 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
        }
        else if (this.array[this.xPos][this.yPos - 1].equalsIgnoreCase("1") && this.visited[this.xPos][this.yPos - 1] != 1 && this.visited[this.xPos][this.yPos - 1] != -1) {
            this.yPos = this.yPos - 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
        }
        else {
            this.yPos = (int) this.stack.top();
            this.stack.pop();
            this.xPos = (int) this.stack.top();
            this.stack.pop();
            this.visited[this.xPos][this.yPos] = -1;
            if (!(this.stack.isEmpty())) {
                if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos - 1][this.yPos].equalsIgnoreCase("1") &&
                        this.visited[this.xPos - 1][this.yPos] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos][this.yPos + 1].equalsIgnoreCase("1") &&
                        this.visited[this.xPos][this.yPos + 1] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos + 1][this.yPos].equalsIgnoreCase("1") &&
                        this.visited[this.xPos + 1][this.yPos] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos][this.yPos - 1].equalsIgnoreCase("1") &&
                        this.visited[this.xPos][this.yPos - 1] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                }
            }
            else {
                value = 2;
            }
        }
        return value;
    }

    public int thirdCase() {
        int value = 0;
         if (this.array[this.xPos - 1][this.yPos].equalsIgnoreCase("E") && this.visited[this.xPos - 1][this.yPos] != 1 && this.visited[this.xPos - 1][this.yPos] != -1) {
            this.xPos = this.xPos - 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
            value = 1;
        }
        else if (this.array[this.xPos][this.yPos + 1].equalsIgnoreCase("E") && this.visited[this.xPos][this.yPos + 1] != 1 && this.visited[this.xPos][this.yPos + 1] != -1) {
            this.yPos = this.yPos + 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
            value = 1;
        }
        else if (this.array[this.xPos + 1][this.yPos].equalsIgnoreCase("E") && this.visited[this.xPos + 1][this.yPos] != 1 && this.visited[this.xPos + 1][this.yPos] != -1) {
            this.xPos = this.xPos + 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
            value = 1;
        }
        else if (this.array[this.xPos - 1][this.yPos].equalsIgnoreCase("1") && this.visited[this.xPos - 1][this.yPos] != 1 && this.visited[this.xPos - 1][this.yPos] != -1) {
            this.xPos = this.xPos - 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
        }
        else if (this.array[this.xPos][this.yPos + 1].equalsIgnoreCase("1") && this.visited[this.xPos][this.yPos + 1] != 1 && this.visited[this.xPos][this.yPos + 1] != -1) {
            this.yPos = this.yPos + 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
        }
        else if (this.array[this.xPos + 1][this.yPos].equalsIgnoreCase("1") && this.visited[this.xPos + 1][this.yPos] != 1 && this.visited[this.xPos + 1][this.yPos] != -1) {

            this.xPos = this.xPos + 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
        }
        else {
            this.yPos = (int) this.stack.top();
            this.stack.pop();
            this.xPos = (int) this.stack.top();
            this.stack.pop();
            this.visited[this.xPos][this.yPos] = -1;
            if (!(this.stack.isEmpty())) {
                if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos - 1][this.yPos].equalsIgnoreCase("1") &&
                        this.visited[this.xPos - 1][this.yPos] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos][this.yPos + 1].equalsIgnoreCase("1") &&
                        this.visited[this.xPos][this.yPos + 1] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos + 1][this.yPos].equalsIgnoreCase("1") &&
                        this.visited[this.xPos + 1][this.yPos] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos][this.yPos - 1].equalsIgnoreCase("1") &&
                        this.visited[this.xPos][this.yPos - 1] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                }
            }
            else {
                value = 2;
            }
        }
        return value;
    }

    public int fourthCase() {
            int value = 0;
             if (this.array[this.xPos - 1][this.yPos].equalsIgnoreCase("E") && this.visited[this.xPos - 1][this.yPos] != 1 && this.visited[this.xPos - 1][this.yPos] != -1) {
            this.xPos = this.xPos - 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
            value = 1;
        } else if (this.array[this.xPos][this.yPos + 1].equalsIgnoreCase("E") && this.visited[this.xPos][this.yPos + 1] != 1 && this.visited[this.xPos][this.yPos + 1] != -1) {
            this.yPos = this.yPos + 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
            value = 1;
        }
        else if (this.array[this.xPos][this.yPos - 1].equalsIgnoreCase("E") && this.visited[this.xPos][this.yPos - 1] != 1 && this.visited[this.xPos][this.yPos - 1] != -1) {
            this.yPos = this.yPos - 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
            value = 1;
        }
        else if (this.array[this.xPos - 1][this.yPos].equalsIgnoreCase("1") && this.visited[this.xPos - 1][this.yPos] != 1 && this.visited[this.xPos - 1][this.yPos] != -1) {
            this.xPos = this.xPos - 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
        } else if (this.array[this.xPos][this.yPos + 1].equalsIgnoreCase("1") && this.visited[this.xPos][this.yPos + 1] != 1 && this.visited[this.xPos][this.yPos + 1] != -1) {
            this.yPos = this.yPos + 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
        }
        else if (this.array[this.xPos][this.yPos - 1].equalsIgnoreCase("1") && this.visited[this.xPos][this.yPos - 1] != 1 && this.visited[this.xPos][this.yPos - 1] != -1) {
            this.yPos = this.yPos - 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
        }
        else {
            this.yPos = (int) this.stack.top();
            this.stack.pop();
            this.xPos = (int) this.stack.top();
            this.stack.pop();
            this.visited[this.xPos][this.yPos] = -1;
            if (!(this.stack.isEmpty())) {
                if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos - 1][this.yPos].equalsIgnoreCase("1") &&
                        this.visited[this.xPos - 1][this.yPos] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos][this.yPos + 1].equalsIgnoreCase("1") &&
                        this.visited[this.xPos][this.yPos + 1] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos + 1][this.yPos].equalsIgnoreCase("1") &&
                        this.visited[this.xPos + 1][this.yPos] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos][this.yPos - 1].equalsIgnoreCase("1") &&
                        this.visited[this.xPos][this.yPos - 1] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                }
            }
            else {
                value = 2;
            }
        }
        return value;
    }

    public int fifthCase() {
        int value = 0;
         if (this.array[this.xPos - 1][this.yPos].equalsIgnoreCase("E") && this.visited[this.xPos - 1][this.yPos] != 1 && this.visited[this.xPos - 1][this.yPos] != -1) {
            this.xPos = this.xPos - 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
            value = 1;
        }
        else if (this.array[this.xPos + 1][this.yPos].equalsIgnoreCase("E") && this.visited[this.xPos + 1][this.yPos] != 1 && this.visited[this.xPos + 1][this.yPos] != -1) {
            this.xPos = this.xPos + 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
            value = 1;
        }
        else if (this.array[this.xPos][this.yPos - 1].equalsIgnoreCase("E") && this.visited[this.xPos][this.yPos - 1] != 1 && this.visited[this.xPos][this.yPos - 1] != -1) {
            this.yPos = this.yPos - 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
            value = 1;
        }
        else if (this.array[this.xPos - 1][this.yPos].equalsIgnoreCase("1") && this.visited[this.xPos - 1][this.yPos] != 1 && this.visited[this.xPos - 1][this.yPos] != -1) {
            this.xPos = this.xPos - 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
        }
        else if (this.array[this.xPos + 1][this.yPos].equalsIgnoreCase("1") && this.visited[this.xPos + 1][this.yPos] != 1 && this.visited[this.xPos + 1][this.yPos] != -1) {
            this.xPos = this.xPos + 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
        } else if (this.array[this.xPos][this.yPos - 1].equalsIgnoreCase("1") && this.visited[this.xPos][this.yPos - 1] != 1 && this.visited[this.xPos][this.yPos - 1] != -1) {
            this.yPos = this.yPos - 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
        }
        else {
            this.yPos = (int) this.stack.top();
            this.stack.pop();
            this.xPos = (int) this.stack.top();
            this.stack.pop();
            this.visited[this.xPos][this.yPos] = -1;
            if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos - 1][this.yPos].equalsIgnoreCase("1") &&
                    this.visited[this.xPos - 1][this.yPos] == 0) {
                this.visited[this.xPos][this.yPos] = 1;
                this.stack.push(this.xPos);
                this.stack.push(this.yPos);
            } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos][this.yPos + 1].equalsIgnoreCase("1") &&
                    this.visited[this.xPos][this.yPos + 1] == 0) {
                this.visited[this.xPos][this.yPos] = 1;
                this.stack.push(this.xPos);
                this.stack.push(this.yPos);
            } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos + 1][this.yPos].equalsIgnoreCase("1") &&
                    this.visited[this.xPos + 1][this.yPos] == 0) {
                this.visited[this.xPos][this.yPos] = 1;
                this.stack.push(this.xPos);
                this.stack.push(this.yPos);
            } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos][this.yPos - 1].equalsIgnoreCase("1") &&
                    this.visited[this.xPos][this.yPos - 1] == 0) {
                this.visited[this.xPos][this.yPos] = 1;
                this.stack.push(this.xPos);
                this.stack.push(this.yPos);
            }
            else {
                value = 2;
            }
        }
        return value;
    }

    public int sixthCase() {
        int value = 0;
          if (this.array[this.xPos][this.yPos + 1].equalsIgnoreCase("E") && this.visited[this.xPos][this.yPos + 1] != 1 && this.visited[this.xPos][this.yPos + 1] != -1) {
            this.yPos = this.yPos + 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
            value = 1;
        } else if (this.array[this.xPos + 1][this.yPos].equalsIgnoreCase("E") && this.visited[this.xPos + 1][this.yPos] != 1 && this.visited[this.xPos + 1][this.yPos] != -1) {
            this.xPos = this.xPos + 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
            value = 1;
        }
        else if (this.array[this.xPos][this.yPos + 1].equalsIgnoreCase("1") && this.visited[this.xPos][this.yPos + 1] != 1 && this.visited[this.xPos][this.yPos + 1] != -1) {
            this.yPos = this.yPos + 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
        } else if (this.array[this.xPos + 1][this.yPos].equalsIgnoreCase("1") && this.visited[this.xPos + 1][this.yPos] != 1 && this.visited[this.xPos + 1][this.yPos] != -1) {
            this.xPos = this.xPos + 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
        }
        else {
            this.yPos = (int) this.stack.top();
            this.stack.pop();
            this.xPos = (int) this.stack.top();
            this.stack.pop();
            this.visited[this.xPos][this.yPos] = -1;
            if (!(this.stack.isEmpty())) {
                if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos - 1][this.yPos].equalsIgnoreCase("1") &&
                        this.visited[this.xPos - 1][this.yPos] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos][this.yPos + 1].equalsIgnoreCase("1") &&
                        this.visited[this.xPos][this.yPos + 1] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos + 1][this.yPos].equalsIgnoreCase("1") &&
                        this.visited[this.xPos + 1][this.yPos] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos][this.yPos - 1].equalsIgnoreCase("1") &&
                        this.visited[this.xPos][this.yPos - 1] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                }
            }
            else {
                value = 2;
            }
        }
        return value;
    }

    public int seventhCase() {
        int value = 0;
         if (this.array[this.xPos - 1][this.yPos].equalsIgnoreCase("E") && this.visited[this.xPos - 1][this.yPos] != 1 && this.visited[this.xPos - 1][this.yPos] != -1) {
            this.xPos = this.xPos - 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
            value = 1;
        }
        else if (this.array[this.xPos][this.yPos + 1].equalsIgnoreCase("E") && this.visited[this.xPos][this.yPos + 1] != 1 && this.visited[this.xPos][this.yPos + 1] != -1) {
            this.yPos = this.yPos + 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
            value = 1;
        }
        else if (this.array[this.xPos - 1][this.yPos].equalsIgnoreCase("1") && this.visited[this.xPos - 1][this.yPos] != 1 && this.visited[this.xPos - 1][this.yPos] != -1) {
            this.xPos = this.xPos - 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
        }
        else if (this.array[this.xPos][this.yPos + 1].equalsIgnoreCase("1") && this.visited[this.xPos][this.yPos + 1] != 1 && this.visited[this.xPos][this.yPos + 1] != -1) {
            this.yPos = this.yPos + 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
        }
        else {
            this.yPos = (int) this.stack.top();
            this.stack.pop();
            this.xPos = (int) this.stack.top();
            this.stack.pop();
            this.visited[this.xPos][this.yPos] = -1;
            if (!(this.stack.isEmpty())) {
                if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos - 1][this.yPos].equalsIgnoreCase("1") &&
                        this.visited[this.xPos - 1][this.yPos] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos][this.yPos + 1].equalsIgnoreCase("1") &&
                        this.visited[this.xPos][this.yPos + 1] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos + 1][this.yPos].equalsIgnoreCase("1") &&
                        this.visited[this.xPos + 1][this.yPos] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos][this.yPos - 1].equalsIgnoreCase("1") &&
                        this.visited[this.xPos][this.yPos - 1] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                }
            }
            else {
                value = 2;
            }
        }
        return value;
    }

    public int eigthCase() {
        int value = 0;
        if (this.array[this.xPos - 1][this.yPos].equalsIgnoreCase("E") && this.visited[this.xPos - 1][this.yPos] != 1 && this.visited[this.xPos - 1][this.yPos] != -1) {
            this.xPos = this.xPos - 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
            value = 1;
        }
        else if (this.array[this.xPos][this.yPos - 1].equalsIgnoreCase("E") && this.visited[this.xPos][this.yPos - 1] != 1 && this.visited[this.xPos][this.yPos - 1] != -1) {
            this.yPos = this.yPos - 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
            value = 1;
        }
        else if (this.array[this.xPos - 1][this.yPos].equalsIgnoreCase("1") && this.visited[this.xPos - 1][this.yPos] != 1 && this.visited[this.xPos - 1][this.yPos] != -1) {
            this.xPos = this.xPos - 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
        }
        else if (this.array[this.xPos][this.yPos - 1].equalsIgnoreCase("1") && this.visited[this.xPos][this.yPos - 1] != 1 && this.visited[this.xPos][this.yPos - 1] != -1) {
            this.yPos = this.yPos - 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
        }
        else {
            this.yPos = (int) this.stack.top();
            this.stack.pop();
            this.xPos = (int) this.stack.top();
            this.stack.pop();
            this.visited[this.xPos][this.yPos] = -1;
            if (!(this.stack.isEmpty())) {
                if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos - 1][this.yPos].equalsIgnoreCase("1") &&
                        this.visited[this.xPos - 1][this.yPos] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos][this.yPos + 1].equalsIgnoreCase("1") &&
                        this.visited[this.xPos][this.yPos + 1] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos + 1][this.yPos].equalsIgnoreCase("1") &&
                        this.visited[this.xPos + 1][this.yPos] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos][this.yPos - 1].equalsIgnoreCase("1") &&
                        this.visited[this.xPos][this.yPos - 1] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                }
            }
            else {
                value = 2;
            }
        }
        return value;
    }

    public int ninthCase() {
        int value = 0;
         if (this.array[this.xPos + 1][this.yPos].equalsIgnoreCase("E") && this.visited[this.xPos + 1][this.yPos] != 1 && this.visited[this.xPos + 1][this.yPos] != -1) {
            this.xPos = this.xPos + 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
            value = 1;
        } else if (this.array[this.xPos][this.yPos - 1].equalsIgnoreCase("E") && this.visited[this.xPos][this.yPos - 1] != 1 && this.visited[this.xPos][this.yPos - 1] != -1) {
            this.yPos = this.yPos - 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
            value = 1;
        }
        else if (this.array[this.xPos + 1][this.yPos].equalsIgnoreCase("1") && this.visited[this.xPos + 1][this.yPos] != 1 && this.visited[this.xPos + 1][this.yPos] != -1) {
            this.xPos = this.xPos + 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
        } else if (this.array[this.xPos][this.yPos - 1].equalsIgnoreCase("1") && this.visited[this.xPos][this.yPos - 1] != 1 && this.visited[this.xPos][this.yPos - 1] != -1) {
            this.yPos = this.yPos - 1;
            this.visited[this.xPos][this.yPos] = 1;
            this.stack.push(this.xPos);
            this.stack.push(this.yPos);
        }
        else {
            this.yPos = (int) this.stack.top();
            this.stack.pop();
            this.xPos = (int) this.stack.top();
            this.stack.pop();
            this.visited[this.xPos][this.yPos] = -1;
            if (!(this.stack.isEmpty())) {
                if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos - 1][this.yPos].equalsIgnoreCase("1") &&
                        this.visited[this.xPos - 1][this.yPos] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos][this.yPos + 1].equalsIgnoreCase("1") &&
                        this.visited[this.xPos][this.yPos + 1] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos + 1][this.yPos].equalsIgnoreCase("1") &&
                        this.visited[this.xPos + 1][this.yPos] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                } else if (this.visited[this.xPos][this.yPos] == -1 && this.array[this.xPos][this.yPos - 1].equalsIgnoreCase("1") &&
                        this.visited[this.xPos][this.yPos - 1] == 0) {
                    this.visited[this.xPos][this.yPos] = 1;
                    this.stack.push(this.xPos);
                    this.stack.push(this.yPos);
                }
            }
            else {
                value = 2;
            }
        }
        return value;
    }
}