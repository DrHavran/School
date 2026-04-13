**How to clone only a specific folder:**

git clone --depth 1 --filter=blob:none --sparse https://github.com/DrHavran/School.git
cd School
git sparse-checkout set <folder-name>
