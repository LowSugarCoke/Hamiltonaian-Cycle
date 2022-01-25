
class HamiltonianCycle
{
    final int V = 5;  //V是整個Adjacency matrix的大小，此範例總共有五個地點，所以V==5
    int path[];  //path[]裡面存的是走過的地點
 
    //判斷下個地點是否可以走
    boolean isSafe(int v, int graph[][], int path[], int pos)
    {
           //判斷是否下個地點可以到達，若true則繼續，若false則return false
        if (graph[path[pos - 1]][v] == 0)
            return false;
 
           //判斷此地點是否已經走過了，若走過則return false;
        for (int i = 0; i < pos; i++)
            if (path[i] == v)
                return false;

        //若以上兩個判斷都通過，則此地點是可以走的，勇敢的走下去
        return true;
    }
 
      //這邊使用backtracking的方法
    boolean hamCycleUtil(int graph[][], int path[], int pos)
    {
      //pos代表走到第幾步，那這邊是只有五個地點，所以範圍是1~5，第一次進來時pos等於1，每走一步加1
      //path[]裡面存的是走過的地點
      //graph[][]存的是地圖

        //判斷pos是否等於V，若是的話代表走到最後一步      
        if (pos == V)
        {
            //判斷是否終點可以走回起點，若可以就皆大歡喜，若否的話則return false，看看接下來還有沒有其他走法
            if (graph[path[pos - 1]][path[0]] == 1)
                return true;
            else
                return false;
        }
 
        //針對每個地點都跑一次試試看，接著後面使用backtracking的方法，一層一層去看，若跑到上面的if(pos==V)的那個判斷return true，就成功拉，若否的話則要退回去原本的地點，直到找到解法
        for (int v = 0; v < V; v++)
        {
            //判斷是否有下一個地點可以走
            if (isSafe(v, graph, path, pos))
            {
                path[pos] = v;  //把path[pos]先定義v，先假定可以走到下個地點，若後來發現不行，則退回去
 
                //繼續recursive的方法，直到return true為止
                if (hamCycleUtil(graph, path, pos + 1) == true)
                    return true;
 
                path[pos] = -1;  //找不到solution或無法走到那個地點，所以把path[pos]定義-1(退回去)
            }
        }
 
        //若全部走完還是無法有任何solution，則return false
        return false;
    }
 
    int hamCycle(int graph[][],int place)
    {
        //這邊先new一個path的array，等等走過的地點就存進去
        path = new int[V];
        for (int i = 0; i < V; i++)  //先初始化
            path[i] = -1;
 
        //這邊假設一開始都是從第0的地點開始，是可以換地點的
        path[0] = place;
        if (hamCycleUtil(graph, path, 1) == false)  //這邊就是跑整個演算法的地方，如果是false的話就會return，如果是true的話，下面就會print找到的solution
        {
            System.out.println("\n找不到solution可以滿足Hamiltonian Cycle");
            return 0;
        }
 
        printSolution(path);  //print找到的solution
        return 1;
    }
 
    //print最後找到的solution
    void printSolution(int path[])
    {
        System.out.println("找到solution，依照下方的路線");
        for (int i = 0; i < V; i++)
        {   
            System.out.print(" " + path[i] + " ");
        }
        System.out.println(" " + path[0] + " ");
    }
 }


class Main {
    public static void main(String args[])
    {
        HamiltonianCycle hamiltonian =  new HamiltonianCycle();
        
        /* 地圖1
           (東京)--(台灣)--(美國)
            |        / \    |
            |       /   \   |
            |     /      \  |
           (加拿大)-------(新加坡)    */
        int graph1[][] = {
          {0, 1, 0, 1, 0},
            {1, 0, 1, 1, 1},
            {0, 1, 0, 0, 1},
            {1, 1, 0, 0, 1},
            {0, 1, 1, 1, 0},
        };
 
        hamiltonian.hamCycle(graph1,2);
 
        /* 地圖2
           (阿拉伯))--(美國)--(印度)
            |        /   \      |
            |       /      \    |
            |     /         \   |
           (菲律賓)          (英國))    */
        int graph2[][] = {
          {0, 1, 0, 1, 0},
            {1, 0, 1, 1, 1},
            {0, 1, 0, 0, 1},
            {1, 1, 0, 0, 0},
            {0, 1, 1, 0, 0},
        };
 

        hamiltonian.hamCycle(graph2,0);
    }
}