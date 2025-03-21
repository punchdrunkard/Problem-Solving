class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Set<String> available = new HashSet<>(Arrays.asList(supplies));
        Queue<Integer> recipeQueue = new LinkedList<>();
        for (int idx = 0; idx < recipes.length; ++idx) {
            recipeQueue.offer(idx);
        }

        List<String> createdRecipes = new ArrayList<>();
        int lastSize = -1;

        while (available.size() > lastSize) {
            lastSize = available.size();
            int queueSize = recipeQueue.size();

            for (int i = 0; i < queueSize; i++) {
                int recipeIdx = recipeQueue.poll();
                boolean canCreate = true;

                for (int j = 0; j < ingredients.get(recipeIdx).size(); j++) {
                    String need = ingredients.get(recipeIdx).get(j);

                    if (!available.contains(need)) {
                        canCreate = false;
                        recipeQueue.offer(recipeIdx);
                        break;
                    }
                }

                if (canCreate) {
                    createdRecipes.add(recipes[recipeIdx]);
                    available.add(recipes[recipeIdx]);
                }
            }
        }

        return createdRecipes;
    }
}