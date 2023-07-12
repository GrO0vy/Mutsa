package heap;

public class BinaryMaxHeap {
    private int[] heap;
    private int size;

    public BinaryMaxHeap(){
        heap = new int[32];
        size = 0;
    }

    public void insert(int item){
        heap[size] = item;
        siftUp(size);
        size++;
    }

    // index 에 존재하는 원소를 자신의 부모와 비교해서
    // heap 의 조건을 만족시키도록 교환을 진행하는 메서드
    private void siftUp(int index){
        while(index > 0){
            int parentIndex = (index - 1) / 2;

            if(heap[index] <= heap[parentIndex]) break;

            int temp = heap[index];
            heap[index] = heap[parentIndex];
            heap[parentIndex] = temp;

            index = parentIndex;
        }
    }

    public int remove(){
        int root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        // 아래로 내리기
        siftDown(0);

        return root;
    }

    private void siftDown(int index){
        while(index < size){
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;

            int maxIndex = index;

            if (leftChild < size && heap[leftChild] > heap[maxIndex]) {
                // 둘을 교환할 준비
                maxIndex = leftChild;
            }
            // 오른쪽도 동일, 이때 왼쪽이 더 컷으면 maxIndex가 갱신되었을 것이고,
            // 아니라면 그대로 루트 였을것이다. 그래서 한번만 비교해도 둘중 더 큰것과 비교가 된다.
            if (rightChild < size && heap[rightChild] > heap[maxIndex]) {
                maxIndex = rightChild;
            }

            if(maxIndex == index) break;

            int temp = heap[index];
            heap[index] = heap[maxIndex];
            heap[maxIndex] = temp;

            index = maxIndex;
        }
    }

    public static void main(String[] args) {
        BinaryMaxHeap maxHeap = new BinaryMaxHeap();
        for (int i = 0; i < 32; i++) {
            maxHeap.insert(i);
        }

        for (int i = 0; i < 32; i++) {
            System.out.println(maxHeap.remove());
        }
    }
}
