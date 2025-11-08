#include <stdio.h>
#include <stdlib.h>

struct Node {
    int data;
    struct Node* next;
};

struct Node* head = NULL;

// Function prototypes
void insertAtBeginning(int value);
void insertAtEnd(int value);
void insertAfterNode(int key, int value);
void deleteFromBeginning();
void deleteFromEnd();
void deleteAfterNode(int key);
void display();

int main() {
    int choice, value, key;

    while (1) {
        printf("\n=== Circular Linked List Menu ===\n");
        printf("1. Insert at Beginning\n");
        printf("2. Insert at End\n");
        printf("3. Insert After a Node\n");
        printf("4. Delete from Beginning\n");
        printf("5. Delete from End\n");
        printf("6. Delete after a Node\n");
        printf("7. Display List\n");
        printf("8. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("Enter value to insert at beginning: ");
                scanf("%d", &value);
                insertAtBeginning(value);
                break;
            case 2:
                printf("Enter value to insert at end: ");
                scanf("%d", &value);
                insertAtEnd(value);
                break;
            case 3:
                printf("Enter key after which to insert: ");
                scanf("%d", &key);
                printf("Enter value to insert: ");
                scanf("%d", &value);
                insertAfterNode(key, value);
                break;
            case 4:
                deleteFromBeginning();
                break;
            case 5:
                deleteFromEnd();
                break;
            case 6:
                printf("Enter key after which to delete: ");
                scanf("%d", &key);
                deleteAfterNode(key);
                break;
            case 7:
                display();
                break;
            case 8:
                printf("Exiting program.\n");
                exit(0);
            default:
                printf("Invalid choice! Try again.\n");
        }
    }

    return 0;
}

// ---------- Insert at Beginning ----------
void insertAtBeginning(int value) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = value;

    if (head == NULL) {
        head = newNode;
        newNode->next = head;
    } else {
        struct Node* temp = head;
        while (temp->next != head)
            temp = temp->next;

        newNode->next = head;
        temp->next = newNode;
        head = newNode;
    }
    printf("Inserted %d at the beginning.\n", value);
}

// ---------- Insert at End ----------
void insertAtEnd(int value) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = value;

    if (head == NULL) {
        head = newNode;
        newNode->next = head;
    } else {
        struct Node* temp = head;
        while (temp->next != head)
            temp = temp->next;

        temp->next = newNode;
        newNode->next = head;
    }
    printf("Inserted %d at the end.\n", value);
}

// ---------- Insert After a Node ----------
void insertAfterNode(int key, int value) {
    if (head == NULL) {
        printf("List is empty! Cannot insert after %d.\n", key);
        return;
    }

    struct Node* temp = head;
    do {
        if (temp->data == key)
            break;
        temp = temp->next;
    } while (temp != head);

    if (temp->data != key) {
        printf("Key %d not found!\n", key);
        return;
    }

    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = value;
    newNode->next = temp->next;
    temp->next = newNode;

    printf("Inserted %d after %d.\n", value, key);
}

// ---------- Delete from Beginning ----------
void deleteFromBeginning() {
    if (head == NULL) {
        printf("List is empty! Nothing to delete.\n");
        return;
    }

    struct Node* temp = head;

    if (head->next == head) { // Only one node
        printf("Deleted element: %d\n", head->data);
        free(head);
        head = NULL;
        return;
    }

    while (temp->next != head)
        temp = temp->next;

    struct Node* deleteNode = head;
    head = head->next;
    temp->next = head;

    printf("Deleted element from beginning: %d\n", deleteNode->data);
    free(deleteNode);
}

// ---------- Delete from End ----------
void deleteFromEnd() {
    if (head == NULL) {
        printf("List is empty! Nothing to delete.\n");
        return;
    }

    struct Node* temp = head;

    if (head->next == head) { // Only one node
        printf("Deleted element: %d\n", head->data);
        free(head);
        head = NULL;
        return;
    }

    while (temp->next->next != head)
        temp = temp->next;

    struct Node* deleteNode = temp->next;
    temp->next = head;

    printf("Deleted element from end: %d\n", deleteNode->data);
    free(deleteNode);
}

// ---------- Delete After a Node ----------
void deleteAfterNode(int key) {
    if (head == NULL) {
        printf("List is empty! Nothing to delete.\n");
        return;
    }

    struct Node* temp = head;
    do {
        if (temp->data == key)
            break;
        temp = temp->next;
    } while (temp != head);

    if (temp->data != key) {
        printf("Key %d not found!\n", key);
        return;
    }

    if (temp->next == head) {
        printf("No node exists after key %d to delete.\n", key);
        return;
    }

    struct Node* deleteNode = temp->next;
    temp->next = deleteNode->next;

    printf("Deleted element %d after key %d.\n", deleteNode->data, key);
    free(deleteNode);
}

// ---------- Display ----------
void display() {
    if (head == NULL) {
        printf("List is empty!\n");
        return;
    }

    struct Node* temp = head;
    printf("Circular Linked List: ");
    do {
        printf("%d -> ", temp->data);
        temp = temp->next;
    } while (temp != head);
    printf("(head)\n");
}
