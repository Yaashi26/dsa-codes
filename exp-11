#include <stdio.h>

#define MAX 20

int adj[MAX][MAX];    // Adjacency matrix
int visited[MAX];     // Visited array
int queue[MAX];       // Queue for BFS
int front = -1, rear = -1;

// Function to add an edge in the adjacency matrix (Directed Graph)
void addEdge(int u, int v) {
    adj[u][v] = 1; // Directed edge from u to v
}

// BFS Function
void BFS(int start, int n) {
    int i;
    for (i = 0; i < n; i++)
        visited[i] = 0;

    front = rear = 0;
    queue[rear] = start;
    visited[start] = 1;

    printf("\nBFS Traversal starting from vertex %d: ", start);

    while (front <= rear) {
        int node = queue[front++];
        printf("%d ", node);

        for (i = 0; i < n; i++) {
            if (adj[node][i] == 1 && visited[i] == 0) {
                queue[++rear] = i;
                visited[i] = 1;
            }
        }
    }
    printf("\n");
}

// DFS Recursive Function
void DFS_Util(int node, int n) {
    int i;
    visited[node] = 1;
    printf("%d ", node);

    for (i = 0; i < n; i++) {
        if (adj[node][i] == 1 && visited[i] == 0) {
            DFS_Util(i, n);
        }
    }
}

// DFS Function
void DFS(int start, int n) {
    int i;
    for (i = 0; i < n; i++)
        visited[i] = 0;

    printf("\nDFS Traversal starting from vertex %d: ", start);
    DFS_Util(start, n);
    printf("\n");
}

int main() {
    int n, edges, u, v, start;

    printf("Enter number of vertices: ");
    scanf("%d", &n);

    // Initialize adjacency matrix
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            adj[i][j] = 0;

    printf("Enter number of edges: ");
    scanf("%d", &edges);

    printf("Enter edges (u v) for directed graph:\n");
    for (int i = 0; i < edges; i++) {
        scanf("%d%d", &u, &v);
        addEdge(u, v);
    }

    printf("Enter starting vertex: ");
    scanf("%d", &start);

    BFS(start, n);
    DFS(start, n);

    return 0;
}
