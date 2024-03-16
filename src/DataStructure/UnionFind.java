package DataStructure;

import java.util.Arrays;

//并查集模板
public class UnionFind {
    private final int[] fa;
    private int size;

    public UnionFind(int n){
        fa = new int[n];
        size = 0;
    }
    //添加
    public void add(int x){
        fa[x] = x;
        size++;
    }
    //找祖先
    public int find(int x){
        if(fa[x] != x) fa[x] = find(fa[x]);
        return fa[x];
    }
    //合并
    public void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x != y){
            fa[x] = fa[y];
            size--;
        }
    }
    //是否同属
    public boolean isConnected(int x, int y){
        return find(x) == find(y);
    }
    //集合数量
    public int getSize(){
        return size;
    }
}


//并查集模板
/**
public class UnionFind {
    private final int[] fa;
    private int[] size;

    public UnionFind(int n){
        fa = new int[n];
        for (int i = 0; i < n; i++) fa[i] = i;
        size = new int[n];
        Arrays.fill(size, 1);
    }
    //添加
    public void add(int x){
        fa[x] = x;
        // size++;
    }
    //找祖先
    public int find(int x){
        if(fa[x] != x) fa[x] = find(fa[x]);
        return fa[x];
    }
    //合并
    public void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x != y){
            if (size[x] > size[y]) {
                fa[y] = x;
                size[x] += size[y];
            } else {
                fa[x] = y;
                size[y] += size[x];
            }
        }
    }
    //是否同属
    public boolean isConnected(int x, int y){
        return find(x) == find(y);
    }
    //集合数量
    public int getSize(int x){
        return size[x];
    }
}
**/