package dnc;

public class BinarySearch {
    public int binarySearch(int[] arr, int target){
        int left = 0;
        int right = arr.length - 1;

        while(left <= right){
            int mid = left + (right - left) / 2;

            // 가운데서 발견: 검색 성공
            if(arr[mid] == target) return mid;
            // 찾는 숫자보다 지금 숫자가 작으면
            // mid 기준 오른쪽 구간을 대상으로 선정
            else if(arr[mid] < target) left = mid + 1;
            // 찾는 숫자보다 지금 숫자가 크면
            // mid 기준 오른쪽 구간을 대상으로 선정
            else right = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int target = 2;
        int index = new BinarySearch().binarySearch(arr, target);

        if (index != -1) {
            System.out.println(index);
        } else {
            System.out.println("탐색 실패");
        }
    }
}
