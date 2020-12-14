package pgdp.filetree;

import java.nio.file.Path;
import java.util.*;

public class Directory extends File {
    public static void main(String[] args) {

    }

	private final List<File> files;

	public Directory(Path path, List<File> files) {
		super(path);

		//!!! if files is null => create an empty list
        if(files==null){
            this.files = new ArrayList<>();
        }
		else
		    this.files = files;
	}

	@Override
	public Iterator<File> iterator() {
		//!!! we need to create a new Iterator class to implement this
        class MyIterator implements Iterator<File>{
            //!!! need a reference to the root directory
            Directory root;

            //!!! need a container to store all the possible iterators of all the folders
            //!!! here we use a queue structure for FIFO
            ArrayList<Iterator<File>> iterators = new ArrayList<>();

            //!!! constructor that builds the iterators
            MyIterator(Directory root){
                this.root = root;

                // the iterator for the root
                ArrayList<File> tmp = new ArrayList<>();
                tmp.add(root);

                this.iterators.add(tmp.iterator());
                this.iterators.addAll(_buildIteratorsRec(this.root)); //!!! this implementation doesn't include the iterator on this Directory itself

            }

            /**
             * helper function that builds all the iterators under the given directory recursively
             * @param dir
             * @return a list of all possible iterators under $(dir)
             */
            private ArrayList<Iterator<File>> _buildIteratorsRec(Directory dir){
                // 0. the to return list
                ArrayList<Iterator<File>> list = new ArrayList<>();

                // 1. first add the root iterator
                list.add(dir.files.iterator());

                // 2. loop the current dir
                dir.files.stream().filter(file -> !file.isRegularFile()) // dirs
                .forEach(subdir->{
                    // soft casting
                    Directory directory = (Directory) subdir;

                    //!!! recursive call
                    list.addAll(_buildIteratorsRec(directory));
                });

                // 3. return
                return list;

            }

            @Override
            public boolean hasNext() {
                //!!! see next()
                return !iterators.isEmpty() && iterators.get(0).hasNext();
            }

            @Override
            public File next() {
                //!!! we apply FIFO strategy for the iterators
                //!!! first check if the queue is already empty
                if(iterators.isEmpty()){
                    throw new NoSuchElementException();
                }

                File next = null;

                //!!! read the frontmost iterator's next
                //* 0. the first iterator has next
                if(iterators.get(0).hasNext()){
                    next = iterators.get(0).next();

                    //!!! adjust the que if needed
                    if(!iterators.get(0).hasNext()){
                        iterators.remove(0);    // ciao
                    }
                }

                //* 1. the first iterator hasn't next
                else{
                    throw new NoSuchElementException();
                }
                return next;
            }
        }
        //* return the implemented iterator
        return new MyIterator(this);
	}

	@Override
	public int getHeight() {
		// case1: no children => 0
        if(files.isEmpty()) return  0;

        // case2: with children => 1 + max{height of children}
        else {
            int max_height = 0;
            for(File file:files){
                if(file.getHeight()>max_height){
                    max_height = file.getHeight();
                }
            }

            return (1+max_height);
        }

	}

	@Override
	public boolean isRegularFile() {
		return false;
	}

	public List<File> getFiles() {
		return files;
	}

}
