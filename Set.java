public class Set {
    int[] set;

    // Constructor
    public Set(int numCells) {
        this.set = new int[numCells];
        for (int i = 0; i < numCells; i++) {
            this.set[i] = -1;
        }
    }

    // Finds root
    public int find(int i) {
        if (set[i] < 0) {
            return i;
        }
        return this.find(this.set[i]);
    }

    // Merges two subsets
    public void merge(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        int sizeA = set[rootA];
        int sizeB = set[rootB];

        if (sizeA < sizeB) {
            this.set[rootA] = rootB;
            this.set[rootB] += sizeA;
        } else {
            this.set[rootB] = rootA;
            this.set[rootA] += sizeB;
        }
    }
    
    // checks if there are no subsets
    public boolean hasNoSubsets() {
        int count = 0;
        for (int i = 0; i < set.length; i++) {
            if (set[i] < 0) {
                count++;
            }
        }
        return count > 1;
    }    
}
