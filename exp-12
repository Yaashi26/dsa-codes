#include <stdio.h>

// Binary Search Function (user-defined)
int binarySearch(int arr[], int n, int key) {
    int low = 0, high = n - 1;

    while (low <= high) {
        int mid = (low + high) / 2;

        if (arr[mid] == key)
            return mid;   // Element found

        if (arr[mid] < key)
            low = mid + 1;  // Search right half
        else
            high = mid - 1; // Search left half
    }
    return -1; // Element not found
}

int main() {
    int n, choice, key;

    printf("Enter number of elements: ");
    scanf("%d", &n);

    int arr[n];
    printf("Enter %d sorted elements: ", n);
    for (int i = 0; i < n; i++)
        scanf("%d", &arr[i]);

    do {
        printf("\nMenu:\n");
        printf("1. Search an element\n");
        printf("2. Display array\n");
        printf("3. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("Enter element to search: ");
                scanf("%d", &key);
                int result = binarySearch(arr, n, key);
                if (result != -1)
                    printf("Element %d found at index %d.\n", key, result);
                else
                    printf("Element %d not found.\n", key);
                break;

            case 2:
                printf("Array elements: ");
                for (int i = 0; i < n; i++)
                    printf("%d ", arr[i]);
                printf("\n");
                break;

            case 3:
                printf("Exiting program...\n");
                break;

            default:
                printf("Invalid choice!\n");
        }
    } while (choice != 3);

    return 0;
}
