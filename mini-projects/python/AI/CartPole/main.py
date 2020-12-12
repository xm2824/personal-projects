import gym
import numpy as np
from flask import Flask
import json
import threading
env = gym.make('CartPole-v1')


def play(env, policy):
    observation = env.reset()                   # status vector of cart pole

    done = False
    score = 0
    Observations = []

    for _ in range(5000):
        Observations += [observation.tolist()]  # Record the observations for normalization and replay

        if done:  # If the simulation was over last iteration, exit loop
            break

        # Pick an action according to the policy matrix
        outcome = np.dot(policy, observation)
        action = 1 if outcome > 0 else 0

        # Make the action, record reward
        observation, reward, done, info = env.step(action)
        print(observation)
        score += reward

    return score, Observations


max = (0, [], [])
app = Flask(__name__, static_folder='.')

@app.route("/data")
def data():
    return json.dumps(max[1])


@app.route('/')
def root():
    return app.send_static_file('./index.html')
for _ in range(100):

    policy = np.random.rand(1, 4) - 0.5
    score, observations = play(env, policy)

    if score > max[0]:
        max = (score, observations, policy)

print('Max Score', max[0])

app.run(host='0.0.0.0', port='3000')
