class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Set<String> suppliesSet = new HashSet<>(Arrays.asList(supplies));
        Set<String> haveIngredients = new HashSet<>(Arrays.asList(supplies));
        
        List<String> result = new ArrayList<>();
        boolean added;
        
        do {
            added = false;

            for (int i = 0; i < recipes.length; i++) {
                if (haveIngredients.contains(recipes[i])) {
                    continue;
                }
                
                boolean canMake = true;
                
                for (String ingredient : ingredients.get(i)) {
                    if (!haveIngredients.contains(ingredient)) {
                        canMake = false;
                        break;
                    }
                }
            
                if (canMake) {
                    haveIngredients.add(recipes[i]);
                    result.add(recipes[i]);
                    added = true;
                }
            }
        } while (added);
        
        return result;
    }
}