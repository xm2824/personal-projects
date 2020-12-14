/**
 * @author ge25pof TaoXiang
 * !!!  This code is written alone by ge25pof TaoXiang and recorded, if any illegal plagirasim happens, I will fight for my unguilty
 * !!!  through legal approaches. (e.g. with the recorded videos)
 * !!!  I've tried to write my codes in an unque way to prevent similary codes, i.e. to avoid extreme coincidence.
 * !!!  E.g. 
 * !!!  1) writted javadoc for each funcion...
 * !!!  2) clear documentations or idea of the codes
 * !!!  3) some instructions are in the same line...
 * !!!
 * !!!  Since last year I gave one of my friend my codes for testing which lead to plagiarism( he forgot to delete it...),
 * !!!  so I can't take any risk this time,seriously speaking!
 * !!!  Thank you for your understanding, really!
 */

package pgdp.files;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FileSearch {

    /**
     * the first parameter is the path of directory/file which we're going to seach in, the following parameters are the to search strings
     * @param args
     */
    public static void main(String[] args)  {
        //!!! if there are not enough arguments => return
        if(args.length<2){
            System.out.println("not enough arguments");
            return;
        }

        //* 0. preparations
        String path = args[0];                              // the path to search in
        String searched[] = new String[args.length-1];      // texts to search
        {
            for (int i = 0; i < searched.length; i++) {
                searched[i] = args[i+1];
            }
        }

        //* 1. call listResults
        List<Result> results = listResults(path, searched);

        //* 2. call toString of each result and appending a breakling line for each result
        results.forEach(r->System.out.println(r.toString()));


        
    }


    /**
     * search the given array of {@code String}s in the specified {@code file}. <p>
     * iterate the lines of the {@code file} only once, search the strings for each line.
     * If at least one string of {@code searched} appears in one line, then add a corresponding {@code Match} to {@code Result}, 
     * If several strings of {@code searched} appear in one line, add only 1 {@code Match},
     * If exceptions happen, then return null
     * @param file here it's a regular file
     * @param searched array of strings which don't have breaking line
     * @return a Result object
     */
    public static Result searchFile(Path path, String[] searched) {
        //* 0. using class Files to read the lines of file from $(path)
        //* iterate the lines of the file, search the strings
        //!!! if exceptions happen, return null
        try {
            //* the returning Result object
            Result ret = new Result(path);

            // line counter, begins with 1
            int lineCounter[] = {1};

            // for each line
            Files.lines(path).forEach(line ->{

                // if this line contains at least one string of searched
                for (String s : searched) {
                    if(line.contains(s)){
                        // then add a corresponding Match
                        ret.addMatch(new Match(lineCounter[0],line));

                        //!!! and break the loop 
                        break;
                    }
                }
                
                //!!! inc line counter
                lineCounter[0] ++;
            
            });

            //* return result
            return ret;
            
        } catch (Exception e) {
            return null;
        }

        
    }


    /**
     * seach the given {@code String}s in all the regular files under directory {@code directory} using {@code searchFile},
     * using {@code walk} to walk all the files and subdirectories under {@code directory}.<p>
     * If IOException comes out, print a report to stdout
     * @param directory
     * @param searched
     * @return
     */
    public static Set<Result> searchDirectory(Path directory, String searched[]) {

        //* the returning set of results
        Set<Result> ret = new HashSet<>();

        //* walk all the files and subdirectories under $(directory)
        try {
            Files.walk(directory)
                    //!!! only need regular files
                    .filter(p->Files.isRegularFile(p))

                    // call seachFile on each regular file
                    .forEach(path -> {
                        Result tmp = searchFile(path,searched);

                        //!!! if result is not null, add it to ret
                        if(tmp!=null) ret.add(tmp);
                    });
            
        } catch (IOException e) {
            System.out.println("An IOExcpetion is thrown during searching");
        }

        //* return
        return ret;
        
    }

    /**
     * sort the returned {@code Result}s of {@code searchDirectory} w.r.t the number of matches in descending ordering
     * @param directory
     * @param searched
     * @return
     */
    public static List<Result> listResults(String directory, String searched[]) {
       //* 0. the results returned by searchDirectory
       Set<Result> results = searchDirectory(Paths.get(directory), searched);

       //* 1. sort the results w.r.t the number of matches in descending ordering
       //* 2. then collect them to List
       List<Result> ret = results.stream().sorted((r1,r2)->r2.getMatches().size()-r1.getMatches().size()).collect(Collectors.toList());

       //* 3. return
       return ret;
    }



}
