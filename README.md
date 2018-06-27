# Beyond-Classical-Searches
To use each question with its assigned algorithm, just uncomment its commented lines in main.java file.<br />

Also you can change algorithms due to your wish.<br />
for example you can replace this line:
```
Hill_Climbing hc = new HCfirstChoose(problem);
```
with this:
```
Hill_Climbing hc = new HCsimple(problem);
```
to use simple hill climbing.<br />
and this:
```
Hill_Climbing hc = new HCrandomRestart(problem);
```
to use random restart hill climbing.<br />
and this:
```
Hill_Climbing hc = new HCrandom(problem);
```
to use random hill climbing.
<br />
<br />
and instead of this line: 
```
SimulatedAnnealing sa = new SAlog(problem, 1000);
```
which is log based simulated annealing, you can use this:
```
SimulatedAnnealing sa = new SAdiv(problem, 1000);

```
to use division based simulated annealing.<br />
and this:
```
SimulatedAnnealing sa = new SAsub(problem, 1000);
```
to use subtarct based simulated annealing.
