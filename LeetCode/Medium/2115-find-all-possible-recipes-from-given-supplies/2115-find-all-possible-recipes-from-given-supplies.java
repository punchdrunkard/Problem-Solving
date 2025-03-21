class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Set<String> availableSupplies = new HashSet<>(Arrays.asList(supplies));
        Map<String, Integer> recipesToIdx = new HashMap<>();
        List<Integer>[] adj = new ArrayList[recipes.length];
        for (int recipesIdx = 0; recipesIdx < recipes.length; recipesIdx++) {
            adj[recipesIdx] = new ArrayList<>();
            recipesToIdx.put(recipes[recipesIdx], recipesIdx);
        }

        int[] inDegree = new int[recipes.length];
        
        // make dependency graph
        for (int recipesIdx = 0; recipesIdx < recipes.length; recipesIdx++) {
            for (String ingredient: ingredients.get(recipesIdx)) {
                // 재료에 다른 레시피가 포함되어 있음
                if (!availableSupplies.contains(ingredient)) {
                    if (recipesToIdx.get(ingredient) != recipesIdx) {
                        adj[recipesToIdx.get(ingredient)].add(recipesIdx);
                    }
                    inDegree[recipesIdx]++;
                }
            }
        }

        List<String> createdRecipes = new ArrayList<>();
        
        // 위상정렬
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) { // inDegree == 0 => 만들어졌음을 의미 
            int recipeIndex = q.poll();
            String created = recipes[recipeIndex];
            createdRecipes.add(created);
            
            // 해당 recipe 가 사용되는 다른 레시피의 inDegree를 줄여주자
            for (int nextIdx: adj[recipeIndex]) {
                inDegree[nextIdx]--;

                if (inDegree[nextIdx] == 0) {
                    q.offer(nextIdx);
                }
            }
        }

        return createdRecipes;
    }
}