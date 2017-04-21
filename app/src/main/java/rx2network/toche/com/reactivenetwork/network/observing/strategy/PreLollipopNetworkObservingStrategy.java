/*
 * Copyright (C) 2016 Piotr Wittchen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rx2network.toche.com.reactivenetwork.network.observing.strategy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.util.Log;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import rx2network.toche.com.reactivenetwork.Connectivity;
import rx2network.toche.com.reactivenetwork.network.observing.NetworkObservingStrategy;
import io.reactivex.Observable;
import io.reactivex.functions.Action;

import static rx2network.toche.com.reactivenetwork.ReactiveNetwork.LOG_TAG;

/**
 * Network observing strategy for Android devices before Lollipop (API 20 or lower).
 * Uses Broadcast Receiver.
 */
public class PreLollipopNetworkObservingStrategy implements NetworkObservingStrategy {

  BroadcastReceiver receiver;
  @Override public Observable<Connectivity> observeNetworkConnectivity(final Context context) {
    final IntentFilter filter = new IntentFilter();
    filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

    return Observable.create(new ObservableOnSubscribe<Connectivity>() {
      @Override
      public void subscribe(final ObservableEmitter<Connectivity> e) throws Exception {
        receiver = new BroadcastReceiver() {
          @Override public void onReceive(Context context, Intent intent) {
            e.onNext(Connectivity.create(context));
          }
        };
        context.registerReceiver(receiver, filter);
      }
    }).doOnDispose(new Action() {
      @Override
      public void run() throws Exception {
        if (receiver != null) {
          context.unregisterReceiver(receiver);
        }
      }
    }).defaultIfEmpty(Connectivity.create());
  }


  @Override public void onError(final String message, final Exception exception) {
    Log.e(LOG_TAG, message, exception);
  }
}
